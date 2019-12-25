package org.locusta.quizserver.repositories;

import org.locusta.quizserver.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByQuestion(String question);
}
