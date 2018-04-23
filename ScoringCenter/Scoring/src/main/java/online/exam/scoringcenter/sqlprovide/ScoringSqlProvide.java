package online.exam.scoringcenter.sqlprovide;

import org.apache.ibatis.jdbc.SQL;

public class ScoringSqlProvide {

    public String findPaperByEmail(String email) {

        return new SQL() {{
            this.SELECT("*").FROM("paper").WHERE("email = #{email}");
        }}.toString();
    }

    public String findPaper(String email, int examID) {
        return new SQL() {{
            this.SELECT("*").FROM("paper").WHERE("email = #{email}")
                    .AND().WHERE("examID = #{examID}");
        }}.toString();
    }

    public String findDetail(String paperID) {

        return new SQL() {{
            this.SELECT("*").FROM("detail").WHERE("paperID = #{paperID}");
        }}.toString();
    }

    public String insertPaper(String email, int examID, int score) {

        return new SQL() {{
            this.INSERT_INTO("paper")
                    .VALUES("email, examID, score", "#{email}, #{examID}, #{score}");
        }}.toString();
    }

    public String insertDetail(int paperID, int questionID, String personalAnswer) {

        return new SQL() {{
            this.INSERT_INTO("detail")
                    .VALUES("paperID, questionID, personalAnswer", "#{paperID}, #{questionID}, #{personalAnswer}");
        }}.toString();
    }
}
