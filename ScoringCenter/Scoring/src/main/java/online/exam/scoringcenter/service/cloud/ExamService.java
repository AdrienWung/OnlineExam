package online.exam.scoringcenter.service.cloud;

import io.swagger.annotations.ApiParam;
import online.exam.scoringcenter.model.cloud.ExamResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ONLINETEST")
public interface ExamService {

    @RequestMapping(value = "/onlineexam/exam/{examID}", method = RequestMethod.GET)
    ExamResponse query(@PathVariable("examID") String examID);
}