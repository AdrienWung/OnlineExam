package online.exam.datacenter.sqlprovide;

import online.exam.datacenter.model.Question;
import org.apache.ibatis.jdbc.SQL;


public class TemplateSqlProvide {

    public String createQuestion(Question question) {

        return new SQL() {{
            this.INSERT_INTO("question").VALUES("course, category, description, selections, answer","#{question.course}, #{question.category}, #{question.description}, #{question.selections}, #{question.answer}");
        }}.toString();
    }

}
