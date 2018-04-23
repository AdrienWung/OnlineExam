package online.exam.info.Service;

import online.exam.info.JsonObjects.ExamJson;
import online.exam.info.JsonObjects.SimpleEmailJson;
import online.exam.info.JsonObjects.SimpleExamJson;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@FeignClient(value = "ONLINETEST")
public interface OnlineExamService {
    @RequestMapping(value = "/onlineexam/query", method = RequestMethod.POST)
    String query(@RequestBody SimpleEmailJson json);
}
