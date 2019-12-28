package org.locusta.quizserver.rest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locusta.quizserver.rest.exceptions.QuizNotFound;
import org.locusta.quizserver.rest.models.QuestionModel;
import org.locusta.quizserver.rest.models.QuizModel;
import org.locusta.quizserver.rest.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(QuizController.class)
class QuizControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuizService service;

    private QuizModel quiz1;
    private QuizModel quiz2;

    @BeforeEach
    public void init(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();

        quiz1=new QuizModel(1L, "Quiz 1");
        quiz1.setQuestions(Set.of(new QuestionModel("Question 1"), new QuestionModel("Question 2")));

        quiz2=new QuizModel(2L, "Quiz 2");
    }

    @Test
    void getAllQuizzes() throws Exception {
        given(service.findAll()).willReturn(Arrays.asList(quiz1, quiz2));

        mvc.perform(get("/quizzes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].title", is("Quiz 1")))
                .andExpect(jsonPath("$[1].title", is("Quiz 2")))
                .andExpect(jsonPath("$[0].questions").doesNotExist())
                .andExpect(jsonPath("$[1].questions").doesNotExist());
    }

    @Test
    void getQuizzById() throws Exception {
        given(service.findById(1L)).willReturn(quiz1);

        mvc.perform(get("/quizzes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Quiz 1")))
                .andExpect(jsonPath("$.questions", hasSize(2)))
                .andExpect(jsonPath("$.questions[*].question",
                        containsInAnyOrder("Question 1","Question 2")));
    }

    @Test
    void getQuizByIdNotFound() throws Exception {
        given(service.findById(999L)).willThrow(new QuizNotFound(999L));

        mvc.perform(get("/quizzes/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}