package online.exam.scoringcenter.model.cloud;

import java.io.Serializable;

public class ExamResponse implements Serializable {
    String status;
    String startTime;
    String endTime;
    String examName;
    String course;
    String questionScore;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

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
        return "ExamResponse{" +
                "status='" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", examName='" + examName + '\'' +
                ", course='" + course + '\'' +
                ", questionScore='" + questionScore + '\'' +
                '}';
    }
}
