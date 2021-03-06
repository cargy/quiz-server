package org.locusta.quizserver.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Quiz {

    @Id @GeneratedValue
    private Long id;
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="quiz_id")
    private Set<Question> questions=new HashSet<>();

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Quiz(String title) {
        this.title=title;
    }

}
