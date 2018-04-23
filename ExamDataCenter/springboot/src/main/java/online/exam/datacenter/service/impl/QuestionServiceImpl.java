package online.exam.datacenter.service.impl;

import online.exam.datacenter.exception.OperationException;
import online.exam.datacenter.mapper.QuestionMapper;
import online.exam.datacenter.model.Question;
import online.exam.datacenter.model.RandomInfo;
import online.exam.datacenter.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    @Transactional(readOnly = true)
    public Question findByQuestionID(String questionID) {
        return questionMapper.findByQuestionID(questionID);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> randomQuestions(RandomInfo randomInfo) throws OperationException {
        List<Question> list = questionMapper.findQuestions(randomInfo.getCourse());
        if (list.size() < randomInfo.getCount()) {
            throw new OperationException("No enough Questions in DB");
        }

        Collections.shuffle(list);
        List<Question> subList = list.subList(0, randomInfo.getCount().intValue());
        for (Question question:subList) {
            question.setAnswer("");
        }
        return subList;
    }

}
