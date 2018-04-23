package online.ui.bean.scoring;

public class AnswerResponseBean {
    private Integer questionID;
    private String description;
    private String category;
    private String selections;
    private String answer;
    private String personalAnswer;

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getPersonalAnswer() {
        return personalAnswer;
    }

    public void setPersonalAnswer(String personalAnswer) {
        this.personalAnswer = personalAnswer;
    }
}
