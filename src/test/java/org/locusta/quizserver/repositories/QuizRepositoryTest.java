package org.locusta.quizserver.repositories;

import fixtures.QuizFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locusta.quizserver.entities.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@EntityScan("org.locusta.quizserver.entities")
@DataJpaTest
@Import(QuizFixtures.class)
public class QuizRepositoryTest {

    @Autowired
    private QuizRepository repository;
    @Autowired
    private QuizFixtures fixtures;

    @BeforeEach
    public void init() {
        Quiz quiz = fixtures.create("Quiz 1", 5);
        repository.save(quiz);
    }

    @Test
    public void testFindAll() {
        List<Quiz> quizzes=repository.findAll();
        assertEquals(1, quizzes.size());

        Quiz quiz = quizzes.get(0);
        assertEquals("Quiz 1", quiz.getTitle());
        assertEquals(5, quiz.getQuestions().size());
        assertEquals(3, quiz.getQuestions().stream().findFirst().orElseThrow().getAnswers().size());
    }

}