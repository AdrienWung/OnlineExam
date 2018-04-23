package online.exam.scoringcenter.model.request;

import java.io.Serializable;

public class History implements Serializable {

    private String email;

    private int examID;

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

    @Override
    public String toString() {
        return "History{" +
                "email='" + email + '\'' +
                ", examID='" + examID + '\'' +
                '}';
    }
}
