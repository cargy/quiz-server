package org.locusta.quizserver.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Answer {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn
    private Question question;

    private String answer;
    private boolean correct;

    public Answer(String answer) {
        this.answer=answer;
    }

    public Answer(String answer, boolean correct) {
        this.answer=answer;
        this.correct=correct;
    }

}
