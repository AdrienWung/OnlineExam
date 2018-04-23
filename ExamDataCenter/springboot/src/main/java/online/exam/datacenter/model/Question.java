package online.exam.datacenter.model;

import java.io.Serializable;

public class Question implements Serializable {

    private static final long serialVersionUID = -4482271496994608810L;

    private Long questionID;

    private String course;

    private String category;

    private String description;

    private String selections;

    private String answer;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", course=" + course +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", selections='" + selections + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
