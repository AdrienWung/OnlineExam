package online.ui.bean.onlineexam;

import online.ui.Json.info.ParticipantsJson;

import java.util.List;

public class QueryExamResponseBean {

    private String examName;
    private String course;
    private Integer questionNum;
    private Integer questionScore;
    private String startTime;
    private String endTime;
    private List<ParticipantsJson> participants;

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

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public Integer getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Integer questionScore) {
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

    public List<ParticipantsJson> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantsJson> participants) {
        this.participants = participants;
    }
}
