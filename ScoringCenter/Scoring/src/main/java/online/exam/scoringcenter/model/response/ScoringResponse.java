package online.exam.scoringcenter.model.response;

import java.io.Serializable;
import java.util.List;

public class ScoringResponse implements Serializable {

    private String email;
    private int examID;
    private String course;
    private int score;
    private String questionScore;
    private List<Question> questions;

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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(String questionScore) {
        this.questionScore = questionScore;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "ScoringResponse{" +
                "email='" + email + '\'' +
                ", examID=" + examID +
                ", course='" + course + '\'' +
                ", score=" + score +
                ", questionScore='" + questionScore + '\'' +
                ", questions=" + questions +
                '}';
    }
}
