package org.locusta.quizserver.repositories;

import fixtures.QuizFixtures;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locusta.quizserver.entities.Question;
import org.locusta.quizserver.entities.Quiz;
import org.locusta.quizserver.entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@EntityScan("org.locusta.quizserver.entities")
@DataJpaTest
@Import(QuizFixtures.class)
public class QuizRepositoryTest {

    @Autowired
    private QuizRepository repository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private QuizFixtures fixtures;

    @BeforeEach
    public void init() {
        Quiz quiz1 = fixtures.create("Quiz 1", 5);
        repository.save(quiz1);

        Quiz quiz2 = new Quiz("Quiz 2");
        repository.save(quiz2);

        Set<Topic> topics = Sets.newLinkedHashSet(new Topic("Topic 1", quiz2), new Topic("Topic 2",quiz2));
        topicRepository.saveAll(topics);

        repository.save(quiz2);
    }

    @Test
    public void testFindAll() {
        List<Quiz> quizzes=repository.findAll();
        assertEquals(2, quizzes.size());

        Quiz quiz = quizzes.get(0);
        assertEquals("Quiz 1", quiz.getTitle());
        assertEquals(5, quiz.getQuestions().size());
        assertEquals(3, quiz.getQuestions().stream().findFirst().orElseThrow().getAnswers().size());
    }

    @Test
    public void findByQuizWithTopics() {
        Optional<Quiz> quiz = repository.findByTitle("Quiz 2");

        assertTrue(quiz.isPresent());
        quiz.get().getQuestions().stream()
                .map(Question::getTopic)
                .forEach(Assertions::assertNotNull);
    }

}