package online.exam.info.Service;

import online.exam.info.JsonObjects.EmailAndExamJson;
import online.exam.info.JsonObjects.ExamJson;
import online.exam.info.JsonObjects.ExamStatus;
import online.exam.info.JsonObjects.SimpleEmailJson;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "SCORING")
public interface ScoringService {
    @RequestMapping(value = "/scoring/exam/state", method = RequestMethod.POST)
    ExamStatus query(@RequestBody EmailAndExamJson json);

}
