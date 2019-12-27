package org.locusta.quizserver.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Topic {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(optional = false)
    private Quiz quiz;

    public Topic(String name, Quiz quiz) {
        this.name=name;
        this.quiz=quiz;
    }
}
