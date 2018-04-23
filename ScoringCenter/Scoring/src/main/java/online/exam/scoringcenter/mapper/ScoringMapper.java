package online.exam.scoringcenter.mapper;

import online.exam.scoringcenter.model.sql.Detail;
import online.exam.scoringcenter.model.sql.Paper;
import online.exam.scoringcenter.sqlprovide.ScoringSqlProvide;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface ScoringMapper {

    @SelectProvider(type = ScoringSqlProvide.class, method = "findPaperByEmail")
    List<Paper> findPaperByEmail(@Param("email") String email);

    @SelectProvider(type = ScoringSqlProvide.class, method = "findPaper")
    Paper findPaper(@Param("email") String email, @Param("examID") int examID);

    @SelectProvider(type = ScoringSqlProvide.class, method = "findDetail")
    List<Detail> findDetail(@Param("paperID") String paperID);

    @InsertProvider(type= ScoringSqlProvide.class, method= "insertPaper")
    void insertPaper(
            @Param("email") String email,
            @Param("examID") int examID,
            @Param("score") int score
    );

    @InsertProvider(type = ScoringSqlProvide.class, method = "insertDetail")
    void insertDetail(
            @Param("paperID") int paperID,
            @Param("questionID") int questionID,
            @Param("personalAnswer") String personalAnswer
    );
}
