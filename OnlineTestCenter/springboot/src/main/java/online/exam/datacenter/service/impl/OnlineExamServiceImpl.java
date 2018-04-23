package online.exam.datacenter.service.impl;

import online.exam.datacenter.VO.ExamRequest;
import online.exam.datacenter.VO.ExamResponse;
import online.exam.datacenter.exception.OperationException;
import online.exam.datacenter.mapper.OnlineExamMapper;
import online.exam.datacenter.model.Exam;
import online.exam.datacenter.model.Student;
import online.exam.datacenter.service.OnlineExamService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Service
public class OnlineExamServiceImpl implements OnlineExamService {
    @Autowired
    private OnlineExamMapper onlineExamMapper;

    @Override
    public List<Student> importFile(InputStream inputStream) throws IOException {
        XSSFSheet xssfSheet = new XSSFWorkbook(inputStream).getSheetAt(0);
        List<Student> students = new ArrayList<Student>();

        //第0行为标题
        for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            students.add(new Student(xssfRow.getCell(0).getStringCellValue()));
        }
        return students;
    }

    @Override
    public ExamResponse createExam(Exam exam) {
    	if(exam.getPassword() == null || exam.getPassword().trim().length() == 0){
    		exam.setPassword(System.currentTimeMillis()+"");
    	}
        onlineExamMapper.createExam(exam);
        exam.setId(onlineExamMapper.getMaxId());
 
        ExamResponse response = new ExamResponse();
        response.setStatus("success");
        response.setExamID(exam.getId());
        response.setExamName(exam.getExamName());
        response.setPassword(exam.getPassword());
        return response;
    }

    @Override
    public Exam getExam(int examID) {
    	Exam exam = onlineExamMapper.getExam(examID);
    	String participants =onlineExamMapper.getParticipants(examID);
    	participants = participants.substring(1, participants.length()-1);
    	String[] emails = participants.split(":");
    	List<Student> students = new ArrayList<Student>();
    	for(int i=0;i<emails.length;i++){
    		Student student = new Student(emails[i]);
    		students.add(student);
    	}
    	
    	exam.setParticipants(students);
        return exam;
    }

    @Override
    public Exam startExam(ExamRequest request) throws OperationException {
        Exam exam = onlineExamMapper.getExam(request.getExamID());
        if (exam == null || !exam.getPassword().equals(request.getPassword()))
            throw new OperationException("考试信息错误");
        return exam;
    }

    @Override
    public List<Exam> getExamByStudent(Student student) {
        return onlineExamMapper.getExamByMail(student.getEmail());
    }

}
