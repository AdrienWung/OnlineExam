package online.ui.vo;

import java.util.ArrayList;
import java.util.List;

public class ScoreVO {

    private int examID;

    private String email;

    private int singleNum;

    private int multiNum;

    private int singleScore;

    private int multiScore;

    private String course;

    private String score;

    private List<AnswerVO> singleList;

    private List<AnswerVO> multiList;

    public ScoreVO() {
        this.singleList = new ArrayList<>();
        this.multiList = new ArrayList<>();
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSingleNum() {
        return singleNum;
    }

    public void setSingleNum(int singleNum) {
        this.singleNum = singleNum;
    }

    public int getMultiNum() {
        return multiNum;
    }

    public void setMultiNum(int multiNum) {
        this.multiNum = multiNum;
    }

    public int getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(int singleScore) {
        this.singleScore = singleScore;
    }

    public int getMultiScore() {
        return multiScore;
    }

    public void setMultiScore(int multiScore) {
        this.multiScore = multiScore;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<AnswerVO> getSingleList() {
        return singleList;
    }

    public List<AnswerVO> getMultiList() {
        return multiList;
    }

    public void addSingle(AnswerVO vo) {
        singleList.add(vo);
    }

    public void addMultiple(AnswerVO vo) {
        multiList.add(vo);
    }

}
