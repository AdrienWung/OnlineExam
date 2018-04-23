package online.ui.bean.question;

import java.util.List;

public class UploadResponseBean {

    private String status;
    private List<QuestionInfoBean> questions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<QuestionInfoBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionInfoBean> questions) {
        this.questions = questions;
    }
}
