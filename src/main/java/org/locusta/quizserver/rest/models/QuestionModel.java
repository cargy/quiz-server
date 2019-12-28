package org.locusta.quizserver.rest.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class QuestionModel {

    @NotNull
    private final String question;

    private Set<AnswerModel> answers;
}
