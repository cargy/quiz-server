package org.locusta.quizserver.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Answer {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Question question;

    private String answer;
    private boolean correct;

}
