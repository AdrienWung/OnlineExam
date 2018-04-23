package online.ui.vo;


import java.util.ArrayList;
import java.util.List;

public class ExamInfoVO {

    private int singleNum;

    private int multiNum;

    private int singleScore;

    private int multiScore;

    private String examTime;

    private List<QuestionVO> singleList;

    private List<QuestionVO> multiList;

    public ExamInfoVO() {
        this.singleList = new ArrayList<>();
        this.multiList = new ArrayList<>();
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

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public List<QuestionVO> getSingleList() {
        return singleList;
    }

    public List<QuestionVO> getMultiList() {
        return multiList;
    }

    public void addSingle(QuestionVO vo) {
        singleList.add(vo);
    }

    public void addMultiple(QuestionVO vo) {
        multiList.add(vo);
    }



}
