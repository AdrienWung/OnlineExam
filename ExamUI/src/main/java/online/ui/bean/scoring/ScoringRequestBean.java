package online.ui.bean.scoring;

import java.util.ArrayList;
import java.util.List;

public class ScoringRequestBean {
    private String email;
    private Integer examID;
    private List<AnswerRequestBean> questions;

    public ScoringRequestBean() {
        this.questions = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getExamID() {
        return examID;
    }

    public void setExamID(Integer examID) {
        this.examID = examID;
    }

    public List<AnswerRequestBean> getQuestions() {
        return questions;
    }

}
