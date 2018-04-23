package online.exam.datacenter.sqlprovide;

import org.apache.ibatis.jdbc.SQL;


public class QuestionSqlProvide {

    public String findByQuestionID(String questionID) {

        return new SQL() {{
            this.SELECT("*").FROM("question").WHERE("questionID = #{questionID}");
        }}.toString();
    }

    public String findByCourse(String course) {

        return new SQL() {{
            this.SELECT("*").FROM("question").WHERE("course = #{course}");
        }}.toString();
    }
}
