package org.locusta.quizserver.repositories;

import org.locusta.quizserver.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
