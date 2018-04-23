package online.ui.bean.onlineexam;

import online.ui.bean.question.QuestionInfoBean;

import java.util.List;

public class StartExamResponseBean {

    private String status;

    private String startTime;

    private String endTime;

    private List<QuestionInfoBean> single;

    private List<QuestionInfoBean> multiple;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<QuestionInfoBean> getSingle() {
        return single;
    }

    public void setSingle(List<QuestionInfoBean> single) {
        this.single = single;
    }

    public List<QuestionInfoBean> getMultiple() {
        return multiple;
    }

    public void setMultiple(List<QuestionInfoBean> multiple) {
        this.multiple = multiple;
    }
}
