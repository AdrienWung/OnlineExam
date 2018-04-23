package online.ui.bean.scoring;

public class HistoryRequestBean {

    private String email;

    private Integer examID;

    public HistoryRequestBean() {
    }

    public HistoryRequestBean(String email, Integer examID) {
        this.email = email;
        this.examID = examID;
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
}
