package org.locusta.quizserver;

import org.locusta.quizserver.entities.Answer;
import org.locusta.quizserver.entities.Question;
import org.locusta.quizserver.entities.Quiz;
import org.locusta.quizserver.entities.Topic;
import org.locusta.quizserver.repositories.QuestionRepository;
import org.locusta.quizserver.repositories.QuizRepository;
import org.locusta.quizserver.repositories.TopicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class LoadSampleData {

    @Bean
    public CommandLineRunner initDatabase(QuizRepository quizRepository,
                                          QuestionRepository questionRepository,
                                          TopicRepository topicRepository) {
        return args -> {
            Quiz quiz1=new Quiz("Quiz 1");

            for(int q=0;q<3;q++) {
                Question question=new Question();
                question.setQuestion("Question "+(q+1));
                for(int a=0;a<3;a++) {
                    Answer answer=new Answer("Answer "+(a+1), a==0);
                    question.addAnswer(answer);
                }
                quiz1.addQuestion(question);
            }
            quizRepository.save(quiz1);

            Quiz quiz2 = new Quiz("Quiz 2");
            quizRepository.save(quiz2);

            Set<Topic> topics = Set.of(new Topic("Topic 1", quiz2), new Topic("Topic 2",quiz2));
            topicRepository.saveAll(topics);

            quizRepository.save(quiz2);

        };
    }
}
