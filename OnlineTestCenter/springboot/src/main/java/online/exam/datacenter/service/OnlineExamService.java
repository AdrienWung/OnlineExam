package online.exam.datacenter.service;

import online.exam.datacenter.VO.ExamRequest;
import online.exam.datacenter.VO.ExamResponse;
import online.exam.datacenter.exception.OperationException;
import online.exam.datacenter.model.Exam;
import online.exam.datacenter.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface OnlineExamService {

    List<Student> importFile(InputStream valueAs) throws IOException;
    

    ExamResponse createExam(Exam exam);

    Exam getExam(int examID);

    Exam startExam(ExamRequest request) throws OperationException;

    List<Exam> getExamByStudent(Student student);
}
