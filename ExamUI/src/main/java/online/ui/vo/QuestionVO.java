package online.ui.vo;

import java.util.ArrayList;
import java.util.List;

public class QuestionVO {

    private int index;

    private int questionID;

    private int score;

    private String description;

    private List<SelectionVO> selections;

    public QuestionVO() {
        this.selections = new ArrayList<>();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addSelections(SelectionVO selection) {
        selections.add(selection);
    }

    public List<SelectionVO> getSelections() {
        return selections;
    }
}
