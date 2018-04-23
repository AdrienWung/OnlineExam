package online.exam.datacenter.mapper;

import online.exam.datacenter.model.Exam;
import online.exam.datacenter.sqlprovide.OnlineExamSqlProvide;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OnlineExamMapper {
//    @Insert("insert into Exam (startTime, endTime, questionNum, questionScore, examName, course, groupBy, participants, password, creator) values(#{startTime}, #{endTime}, #{questionNum}, #{questionScore}, #{examName}, #{course}, #{groupBy}, #{participants}, #{password}, #{creator})")
    @SelectProvider(type = OnlineExamSqlProvide.class, method = "createExam")
    void createExam(Exam examInfo);
    
    @SelectProvider(type = OnlineExamSqlProvide.class, method = "getMaxId")
    int getMaxId();
    
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    

    @SelectProvider(type = OnlineExamSqlProvide.class, method = "getExam")
    Exam getExam(@Param("examId") int examId);
    
    @SelectProvider(type = OnlineExamSqlProvide.class, method = "getParticipants")
    String getParticipants(@Param("examId") int examId);

    @SelectProvider(type = OnlineExamSqlProvide.class, method = "getExamByMail")
    List<Exam> getExamByMail(String creator);
}
