package org.locusta.quizserver.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Quiz {

    @Id @GeneratedValue
    private Long id;
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Question> questions=new HashSet<>();

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
