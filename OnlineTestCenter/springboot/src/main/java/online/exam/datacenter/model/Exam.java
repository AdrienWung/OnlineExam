package online.exam.datacenter.model;

import java.io.Serializable;
import java.util.List;

public class Exam implements Serializable {
    private int id;
    private String startTime;
    private String endTime;
    private String creator;
    private int questionNum;
    private int questionScore;
    private String examName;
    private String course;
    private String groupBy;
    private String password;
    private List<Student> participants;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public int getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}
	public int getQuestionScore() {
		return questionScore;
	}
	public void setQuestionScore(int questionScore) {
		this.questionScore = questionScore;
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
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Student> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Student> participants) {
		this.participants = participants;
	}
	@Override
	public String toString() {
		return "Exam [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", creator=" + creator
				+ ", questionNum=" + questionNum + ", questionScore=" + questionScore + ", examName=" + examName
				+ ", course=" + course + ", groupBy=" + groupBy + ", password=" + password + ", participants="
				+ participants + "]";
	}

	
	
    
    

}
