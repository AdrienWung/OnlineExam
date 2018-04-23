package online.ui.Json;

import online.ui.Json.info.ParticipantsJson;

import java.util.List;

public class LoadStudentListResponse {
    String status;
    List<ParticipantsJson> participants;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ParticipantsJson> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantsJson> participants) {
        this.participants = participants;
    }
}
