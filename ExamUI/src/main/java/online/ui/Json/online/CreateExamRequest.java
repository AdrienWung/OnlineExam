package online.ui.Json.online;

import online.ui.Json.info.ParticipantsJson;

import java.util.List;

public class CreateExamRequest {
    String questionNum;
    String questionScore;
    String startTime;
    String endTime;
    String examName;
    String course;
    String groupBy;
    List<ParticipantsJson> participants;

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
    }

    public String getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(String questionScore) {
        this.questionScore = questionScore;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public List<ParticipantsJson> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantsJson> participants) {
        this.participants = participants;
    }
}
