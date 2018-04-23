package online.exam.scoringcenter.service.stub;

import io.swagger.annotations.ApiParam;
import online.exam.scoringcenter.model.cloud.QuestionResponse;
import online.exam.scoringcenter.service.cloud.QuestionService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by JiachenWang on 2017/12/15.
 */
//@Component
//@Service
public class QuestionServiceImpl implements QuestionService {

    @Override
    public QuestionResponse query(@ApiParam @PathVariable("questionID") String questionID) {
        QuestionResponse response = new QuestionResponse();
        response.setCourse("JAVA");
        response.setAnswer("A");
        response.setCategory("single");
        response.setQuestionID(1);
        response.setDescription("1+1?");
        response.setSelections("a.sss;b.ddd");
        return response;
    }
}
