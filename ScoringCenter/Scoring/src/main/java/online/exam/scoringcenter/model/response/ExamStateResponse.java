package online.exam.scoringcenter.model.response;

/**
 * Created by JiachenWang on 2017/12/17.
 */
public class ExamStateResponse {

    private String email;

    private int examID;

    private String state;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ExamStateResponse{" +
                "email='" + email + '\'' +
                ", examID=" + examID +
                ", state='" + state + '\'' +
                '}';
    }
}
