package online.ui.Interface;

import online.ui.bean.question.QuestionInfoBean;
import online.ui.bean.question.UploadResponseBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "EXAMDATA")
public interface QuestionService {

    @RequestMapping(value = "/question/",method = RequestMethod.GET)
    QuestionInfoBean getSingleQuestion(@PathVariable("questionID") Integer questionID);

    @RequestMapping(value = "/question/random",method = RequestMethod.POST)
    List<QuestionInfoBean> random(@RequestParam("catagory") String catagory, @RequestParam("count") Integer count);
}
