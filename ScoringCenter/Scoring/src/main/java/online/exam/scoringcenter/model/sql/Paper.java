package online.exam.scoringcenter.model.sql;

import java.io.Serializable;

public class Paper implements Serializable {

    private int paperID;

    private String email;

    private int examID;

    private int score;

    public int getPaperID() {
        return paperID;
    }

    public void setPaperID(int paperID) {
        this.paperID = paperID;
    }

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "paperID=" + paperID +
                ", email='" + email + '\'' +
                ", examID=" + examID +
                ", score=" + score +
                '}';
    }
}
