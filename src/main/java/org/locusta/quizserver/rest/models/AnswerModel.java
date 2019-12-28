package org.locusta.quizserver.rest.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class AnswerModel {

    @NotNull
    private final String answer;

    @NotNull
    private final boolean correct;

}
