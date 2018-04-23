package online.ui.Json.info;

import online.ui.Json.ExamJson;
import online.ui.Json.info.CourseJson;

import java.util.List;

public class LoginRespJson {
    String status;
    String identity;
    String msg;
    List<ExamJson> exams;
    List<CourseJson>  courses;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ExamJson> getExams() {
        return exams;
    }

    public void setExams(List<ExamJson> exams) {
        this.exams = exams;
    }

    public List<CourseJson> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseJson> courses) {
        this.courses = courses;
    }
}
