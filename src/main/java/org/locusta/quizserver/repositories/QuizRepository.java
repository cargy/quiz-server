package org.locusta.quizserver.repositories;

import org.locusta.quizserver.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    Optional<Quiz> findByTitle(String title);
}
