package online.exam.scoringcenter.model.response;

import java.io.Serializable;

public class ExamScoreResponse implements Serializable {

    private String email;

    private int score;

    private String state;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ExamStateResponse{" +
                "email='" + email + '\'' +
                ", score=" + score +
                ", state='" + state + '\'' +
                '}';
    }
}
