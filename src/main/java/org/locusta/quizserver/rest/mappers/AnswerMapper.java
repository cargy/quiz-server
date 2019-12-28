package org.locusta.quizserver.rest.mappers;

import org.locusta.quizserver.entities.Answer;
import org.locusta.quizserver.rest.models.AnswerModel;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    public AnswerModel convertToModel(Answer entity) {
        return new AnswerModel(entity.getAnswer(),entity.isCorrect());
    }
}
