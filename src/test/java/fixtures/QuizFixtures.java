package fixtures;

import org.locusta.quizserver.entities.Question;
import org.locusta.quizserver.entities.Quiz;
import org.locusta.quizserver.entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(QuestionFixtures.class)
public class QuizFixtures {

    private QuestionFixtures questionFixtures;

    @Autowired
    public void setQuestionFixtures(QuestionFixtures questionFixtures) {
        this.questionFixtures=questionFixtures;
    }

    public Quiz create(String quizTitle, int numberOfQuestions) {
        Quiz quiz=new Quiz();
        quiz.setTitle(quizTitle);
        for(int i=0;i<numberOfQuestions;i++){
            quiz.addQuestion(questionFixtures.create("Question " + (i+1), i%3,
                    "Answer 1", "Answer 2", "Answer 3"));
        }
        return quiz;
    }

}
