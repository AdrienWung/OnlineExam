package online.exam.scoringcenter.model.request;

import java.io.Serializable;

public class PersonalAnswer implements Serializable {

    private int questionID;

    private String personalAnswer;

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getPersonalAnswer() {
        return personalAnswer;
    }

    public void setPersonalAnswer(String personalAnswer) {
        this.personalAnswer = personalAnswer;
    }

    @Override
    public String toString() {
        return "PersonalAnswer{" +
                "questionID=" + questionID +
                ", personalAnswer='" + personalAnswer + '\'' +
                '}';
    }
}
