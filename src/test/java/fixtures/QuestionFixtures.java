package fixtures;

import org.assertj.core.util.Sets;
import org.locusta.quizserver.entities.Answer;
import org.locusta.quizserver.entities.Question;
import org.locusta.quizserver.entities.Quiz;
import org.locusta.quizserver.entities.Topic;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class QuestionFixtures {

    public Question create(String question, int correctIndex, String... answers) {
        return create(question, null, correctIndex, answers);
    }

    public Question create(String question, Topic topic, int correctIndex, String... answers) {
        Set<Answer> answersSet = new HashSet<>();
        for(int i=0;i<answers.length;i++) {
            answersSet.add(new Answer(answers[i], i==correctIndex));
        }

        Question quiz = new Question();
        quiz.setQuestion(question);
        quiz.addAnswers(answersSet);
        quiz.setTopic(topic);
        return quiz;
    }

    public Set<Question> createWithTopics(Quiz quiz, Topic... topics) {
        Set<Question> questions= Sets.newHashSet();
        for(Topic topic:topics){
            for (int i=0;i<3;i++) {
                Question question = create(topic.getName() + " - Question " + (i + 1), topic,
                        i % 3, "Answer 1", "Answer 2", "Answer 3");
                questions.add(question);
            }
        }
        return questions;
    }
}
