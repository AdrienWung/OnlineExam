package online.exam.scoringcenter.service.stub;

import io.swagger.annotations.ApiParam;
import online.exam.scoringcenter.model.cloud.ExamResponse;
import online.exam.scoringcenter.service.cloud.ExamService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by JiachenWang on 2017/12/15.
 */
//@Component
//@Service
public class ExamServiceImpl implements ExamService {

    @Override
    public ExamResponse query(@ApiParam @PathVariable("examID") String examID) {
        ExamResponse response = new ExamResponse();
        response.setStatus("success");
        response.setStartTime("2017-11-11 13:00:00");
        response.setEndTime("2017-11-11 14:00:00");
        response.setCourse("JAVA");
        response.setExamName("finaltest");
        response.setQuestionScore("5");
        return response;
    }
}
