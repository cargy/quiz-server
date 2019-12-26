package org.locusta.quizserver.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locusta.quizserver.entities.Answer;
import org.locusta.quizserver.entities.Question;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@EntityScan("org.locusta.quizserver.entities")
@DataJpaTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    public void init() {
        Answer answer1 = new Answer();
        answer1.setAnswer("Answer 1");
        answer1.setCorrect(true);
        Answer answer2 = new Answer();
        answer2.setAnswer("Answer 2");
        Answer answer3 = new Answer();
        answer3.setAnswer("Answer 3");

        Question question = new Question();
        question.setQuestion("Question?");
        question.addAnswers(Sets.newSet(answer1, answer2, answer3));
        questionRepository.save(question);

    }

    @Test
    public void testFindQuestionById() {
        Optional<Question> questionOptional = questionRepository.findByQuestion("Question?");

        assertTrue(questionOptional.isPresent());
        Question question = questionOptional.get();

        assertEquals("Question?", question.getQuestion());
        assertEquals(3, question.getAnswers().size());

        int index = 0;
        for(Answer answer:question.getAnswers()) {
            assertEquals("Answer " + ++index, answer.getAnswer());
            assertEquals(index == 1, answer.isCorrect());
        }
    }
}