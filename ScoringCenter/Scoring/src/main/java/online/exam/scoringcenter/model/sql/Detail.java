package online.exam.scoringcenter.model.sql;

import java.io.Serializable;

public class Detail implements Serializable {
    int paperID;
    int questionID;
    String personalAnswer;

    public int getPaperID() {
        return paperID;
    }

    public void setPaperID(int paperID) {
        this.paperID = paperID;
    }

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
        return "Detail{" +
                "paperID='" + paperID + '\'' +
                ", questionID='" + questionID + '\'' +
                ", personalAnswer='" + personalAnswer + '\'' +
                '}';
    }
}
