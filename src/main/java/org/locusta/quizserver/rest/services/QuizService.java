package org.locusta.quizserver.rest.services;

import org.locusta.quizserver.repositories.QuizRepository;
import org.locusta.quizserver.rest.exceptions.QuizNotFound;
import org.locusta.quizserver.rest.mappers.QuizMapper;
import org.locusta.quizserver.rest.models.QuizModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository repository;
    private final QuizMapper mapper;

    @Autowired
    public QuizService(QuizRepository repository, QuizMapper mapper) {
        this.repository=repository;
        this.mapper=mapper;
    }

    public Collection<QuizModel> findAll() {
        return repository.findAll().stream()
                .map(mapper::convertToModel)
                .collect(Collectors.toList());
    }

    public QuizModel findById(Long quizId) {
        return repository.findById(quizId)
                .map(mapper::convertToModel)
                .orElseThrow(()->new QuizNotFound(quizId));
    }
}
