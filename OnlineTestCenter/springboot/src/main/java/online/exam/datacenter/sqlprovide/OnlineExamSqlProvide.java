package online.exam.datacenter.sqlprovide;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.jdbc.SQL;

import online.exam.datacenter.model.Exam;

public class OnlineExamSqlProvide {
	
	
	public String createExam(Exam exam) {
		
		String participants = "'{";
		for(int i=0;i<exam.getParticipants().size()-1;i++){
			participants += exam.getParticipants().get(i).getEmail()+":";
		}
		participants += exam.getParticipants().get(exam.getParticipants().size()-1).getEmail();
		participants += "}'";
		
		final String students=participants;
		return new SQL() {{
            this.INSERT_INTO("Exam").VALUES("startTime, endTime, questionNum, questionScore, examName, course, groupBy, participants, password, creator","#{startTime}, #{endTime}, #{questionNum}, #{questionScore}, #{examName}, #{course}, #{groupBy}, "+students+", #{password}, #{creator}");
//            this.SELECT("'max(id)'").FROM("OnlineTest.Exam");
        }}.toString();

        
        
        
    }
	
	public String getMaxId(){
        return new SQL() {{
            this.SELECT("max(id)").FROM("Exam");
        }}.toString();
	}
	
	
//    public String createExam(Exam exam) {
//    	String participants = exam.getParticipants().toString();
//        return new SQL() {{
//            this.INSERT_INTO("Exam").VALUES("course_id, category, description, selections, answer","#{exam.course_id}, #{question.category}, #{question.description}, #{question.selections}, #{question.answer}").SELECT("examID");
//
//        }}.toString();
//    }

    public String getExam(int examId) {

        return new SQL() {{
            this.SELECT("*").FROM("Exam").WHERE("id = #{examId}");
        }}.toString();
    }
    
    
    public String getParticipants(int examId){
    	return new SQL() {{
            this.SELECT("participants").FROM("Exam").WHERE("id = #{examId}");
        }}.toString();
    }

    public String getExamByMail(String email) {

        return new SQL() {{
            this.SELECT("*").FROM("Exam").WHERE("creator = #{email} or participants like CONCAT('%',#{email},'%')  ");
        }}.toString();
    }


}
