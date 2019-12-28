package org.locusta.quizserver.rest.mappers;

import org.locusta.quizserver.entities.Quiz;
import org.locusta.quizserver.rest.models.QuizModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuizMapper {

    private final QuestionMapper questionMapper;

    @Autowired
    public QuizMapper(QuestionMapper questionMapper){
        this.questionMapper=questionMapper;
    }

    public QuizModel convertToModel(Quiz entity) {
        QuizModel model = new QuizModel(entity.getId(), entity.getTitle());
        model.setQuestions(entity.getQuestions().stream()
                .map(questionMapper::convertToModel)
                .collect(Collectors.toSet())
        );
        return model;
    }
}
