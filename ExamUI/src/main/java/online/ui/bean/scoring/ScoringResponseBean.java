package online.ui.bean.scoring;

import java.util.List;

public class ScoringResponseBean {
    private String email;
    private Integer examID;
    private String course;
    private Integer score;
    private Integer questionScore;
    private List<AnswerResponseBean> questions;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getExamID() {
        return examID;
    }

    public void setExamID(Integer examID) {
        this.examID = examID;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Integer questionScore) {
        this.questionScore = questionScore;
    }

    public List<AnswerResponseBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<AnswerResponseBean> questions) {
        this.questions = questions;
    }
}
