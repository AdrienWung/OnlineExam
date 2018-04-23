package online.ui.Json;

import online.ui.Json.info.ParticipantsJson;

import java.util.List;

public class ScoreStateRequest {
    String examID;
    List<ParticipantsJson> emails;


    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public List<ParticipantsJson> getEmails() {
        return emails;
    }

    public void setEmails(List<ParticipantsJson> emails) {
        this.emails = emails;
    }
}
