package online.ui.bean.scoring;

public class AnswerRequestBean {
    private Integer questionID;
    private String personalAnswer;

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public String getPersonalAnswer() {
        return personalAnswer;
    }

    public void setPersonalAnswer(String personalAnswer) {
        this.personalAnswer = personalAnswer;
    }
}
