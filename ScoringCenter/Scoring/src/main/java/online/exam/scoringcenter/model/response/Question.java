package online.exam.scoringcenter.model.response;

import java.io.Serializable;

public class Question implements Serializable {

    private int questionID;

    private String description;

    private String selections;

    private String category;

    private String answer;

    private String personalAnswer;

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPersonalAnswer() {
        return personalAnswer;
    }

    public void setPersonalAnswer(String personalAnswer) {
        this.personalAnswer = personalAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", description='" + description + '\'' +
                ", selections='" + selections + '\'' +
                ", category='" + category + '\'' +
                ", answer='" + answer + '\'' +
                ", personalAnswer='" + personalAnswer + '\'' +
                '}';
    }
}
