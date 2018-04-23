package online.exam.scoringcenter.model.request;

import java.io.Serializable;
import java.util.List;

public class Scoring implements Serializable {

    private String email;

    private int examID;

    List<PersonalAnswer> questions;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public List<PersonalAnswer> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PersonalAnswer> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Scoring{" +
                "email='" + email + '\'' +
                ", examID='" + examID + '\'' +
                ", questions=" + questions +
                '}';
    }
}
