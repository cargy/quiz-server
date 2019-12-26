package org.locusta.quizserver.repositories;

import fixtures.QuestionFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locusta.quizserver.entities.Answer;
import org.locusta.quizserver.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@EntityScan("org.locusta.quizserver.entities")
@DataJpaTest
@Import(QuestionFixtures.class)
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository repository;
    @Autowired
    private QuestionFixtures fixtures;

    @BeforeEach
    public void init() {
        Question question=fixtures.create("Question?", 0, "Answer 1", "Answer 2", "Answer 3");
        repository.save(question);
    }

    @Test
    public void testFindQuestionById() {
        Optional<Question> questionOptional = repository.findByQuestion("Question?");

        assertTrue(questionOptional.isPresent());
        Question question = questionOptional.get();

        assertEquals("Question?", question.getQuestion());
        assertEquals(3, question.getAnswers().size());

        assertEquals("Answer 1", question.getAnswers().stream()
                .filter(Answer::isCorrect)
                .findAny().orElseThrow()
                .getAnswer());
    }
}