package online.exam.datacenter.service;

import online.exam.datacenter.exception.OperationException;
import online.exam.datacenter.model.Question;
import online.exam.datacenter.model.RandomInfo;

import java.util.List;

public interface QuestionService {

    Question findByQuestionID(String questionID);

    List<Question> randomQuestions(RandomInfo randomInfo) throws OperationException;
}
