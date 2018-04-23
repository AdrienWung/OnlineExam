package online.exam.scoringcenter.model.request;

import java.io.Serializable;

public class Query implements Serializable {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Query{" +
                "email='" + email + '\'' +
                '}';
    }
}
