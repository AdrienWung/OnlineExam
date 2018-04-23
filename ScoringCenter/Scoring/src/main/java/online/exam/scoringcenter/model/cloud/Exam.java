package online.exam.scoringcenter.model.cloud;

import java.io.Serializable;

public class Exam implements Serializable {
    String examName;
    String course;
    String questionScore;

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(String questionScore) {
        this.questionScore = questionScore;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examName='" + examName + '\'' +
                ", course='" + course + '\'' +
                ", questionScore='" + questionScore + '\'' +
                '}';
    }
}
