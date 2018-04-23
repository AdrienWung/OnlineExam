package online.exam.datacenter.VO;

import java.io.Serializable;

public class QuestionVO implements Serializable{
	public long questionID;
	public String category;
	public String description;
	public String selections;
	public int score;
	public long getQuestionID() {
		return questionID;
	}
	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSelections() {
		return selections;
	}
	public void setSelections(String selections) {
		this.selections = selections;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "{questionID=" + questionID + ", category='" + category + "', description='" + description
				+ "', selections='" + selections + "', score=" + score + "}";
	}
	public QuestionVO(long questionID, String category, String description, String selections, int score) {
		super();
		this.questionID = questionID;
		this.category = category;
		this.description = description;
		this.selections = selections;
		this.score = score;
	}

	
	

}
