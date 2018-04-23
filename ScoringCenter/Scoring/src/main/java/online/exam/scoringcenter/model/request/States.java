package online.exam.scoringcenter.model.request;

import java.io.Serializable;
import java.util.List;

public class States implements Serializable {

    private int examID;

    private List<SingleEmail> emails;

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public List<SingleEmail> getEmails() {
        return emails;
    }

    public void setEmails(List<SingleEmail> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "States{" +
                "examID='" + examID + '\'' +
                ", emails=" + emails +
                '}';
    }
}
