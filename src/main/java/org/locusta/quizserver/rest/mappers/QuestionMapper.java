package org.locusta.quizserver.rest.mappers;

import org.locusta.quizserver.entities.Question;
import org.locusta.quizserver.rest.models.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private final AnswerMapper answerMapper;

    @Autowired
    public QuestionMapper(AnswerMapper answerMapper){
        this.answerMapper=answerMapper;
    }

    public QuestionModel convertToModel(Question entity) {
        QuestionModel model=new QuestionModel(entity.getQuestion());
        model.setAnswers(entity.getAnswers().stream()
                .map(answerMapper::convertToModel)
                .collect(Collectors.toSet()));
        return model;
    }

}
