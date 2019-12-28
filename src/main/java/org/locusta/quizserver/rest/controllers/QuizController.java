package org.locusta.quizserver.rest.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.locusta.quizserver.rest.models.QuizModel;
import org.locusta.quizserver.rest.models.ModelViews;
import org.locusta.quizserver.rest.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService=quizService;
    }

    @GetMapping
    @JsonView(ModelViews.Summary.class)
    public Collection<QuizModel> getAllQuizzes() {
       return quizService.findAll();
    }

    @GetMapping("/{quizId}")
    public QuizModel getQuizById(@PathVariable Long quizId) {
        return quizService.findById(quizId);
    }
}
