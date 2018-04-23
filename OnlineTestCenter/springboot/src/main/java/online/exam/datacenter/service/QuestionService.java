package online.exam.datacenter.service;

import online.exam.datacenter.VO.RandomInfo;
import online.exam.datacenter.model.Question;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(value = "EXAMDATA")
public interface QuestionService {
    @RequestMapping(value = "/rest/question/{questionId}", method = RequestMethod.GET)
    Question findQuestion(@PathVariable("questionId") int questionId);

    @RequestMapping(value = "/rest/question/random", method = RequestMethod.POST)
    List<Question> findRandomQuestion(@RequestBody RandomInfo randomInfo);
}
