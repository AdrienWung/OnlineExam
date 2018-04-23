package online.exam.scoringcenter.model.response;

import java.io.Serializable;

public class QueryResponse implements Serializable {
    String examID;
    String examName;

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    @Override
    public String toString() {
        return "QueryResponse{" +
                "examID='" + examID + '\'' +
                ", examName='" + examName + '\'' +
                '}';
    }
}
