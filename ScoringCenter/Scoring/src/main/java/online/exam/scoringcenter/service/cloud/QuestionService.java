package online.exam.scoringcenter.service.cloud;

import online.exam.scoringcenter.model.cloud.QuestionResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "EXAMDATA")
public interface QuestionService {

    @RequestMapping(value = "/rest/question/{questionID}", method = RequestMethod.GET)
    QuestionResponse query(@PathVariable("questionID") String questionID);
}