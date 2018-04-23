package online.ui.Interface;

import online.ui.Json.EmailJson;
import online.ui.Json.GeneralResponse;
import online.ui.Json.info.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "INFO")
public interface InfoService {
    @RequestMapping(value = "/regist/student",method = RequestMethod.POST)
    GeneralResponse studentRegist(@RequestBody StudentRegistJson json);

    @RequestMapping(value = "/regist/teacher",method = RequestMethod.POST)
    GeneralResponse teacherRegist(@RequestBody TeacherRegistJson json);


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    LoginRespJson login(@RequestBody LoginRequestJson json);

    @RequestMapping(value="/newCourse",method = RequestMethod.POST)
    GeneralResponse newCourse(@RequestBody NewCourseJson json) ;

    @RequestMapping(value="/register/sendVerCode",method=RequestMethod.POST)
    GeneralResponse getVerCode(@RequestBody SimpleEmailJson json);

    @RequestMapping(value="/sendEmail",method=RequestMethod.POST)
    GeneralResponse sendEmail(@RequestBody EmailJson json);

}
