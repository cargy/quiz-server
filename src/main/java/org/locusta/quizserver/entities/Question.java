package org.locusta.quizserver.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "answers")
public class Question {

    @Id @GeneratedValue
    private Long id;

    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<>();

    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
        answers.add(answer);
    }

    public void addAnswers(Set<Answer> answers) {
        answers.forEach(this::addAnswer);
    }

}
