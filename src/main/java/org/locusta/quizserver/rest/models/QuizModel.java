package org.locusta.quizserver.rest.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class QuizModel {

    @NotNull @JsonView(ModelViews.Summary.class)
    private final Long id;

    @NotNull @JsonView(ModelViews.Summary.class)
    private final String title;

    private Set<QuestionModel> questions;
}
