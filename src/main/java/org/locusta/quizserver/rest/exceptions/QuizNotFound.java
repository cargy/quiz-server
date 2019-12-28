package org.locusta.quizserver.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuizNotFound extends RuntimeException{

    private final Long resourceId;

    public QuizNotFound(Long resourceId) {
        super("Quiz with "+resourceId+" not found");
        this.resourceId=resourceId;
    }
}
