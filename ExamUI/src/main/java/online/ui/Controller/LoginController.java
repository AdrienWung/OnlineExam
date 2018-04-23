package online.ui.Controller;

import online.ui.Interface.ExamDataService;
import online.ui.Interface.InfoService;
import online.ui.Json.DownloadResponse;
import online.ui.Json.info.CourseJson;
import online.ui.Json.ExamJson;
import online.ui.Json.info.LoginRequestJson;
import online.ui.Json.info.LoginRespJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("email")
public class LoginController {
    @Autowired
    InfoService infoService;

    @Autowired
    ExamDataService examDataService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, Model model){
        //测试用
//        List<CourseJson> courseJsons=new ArrayList<>();
//        CourseJson courseJson=new CourseJson();
//        courseJson.setCourseName("JAVA");
//        courseJsons.add(courseJson);
//        courseJson=new CourseJson();
//        courseJson.setCourseName("C++");
//        courseJsons.add(courseJson);
//        //结束课程注入
//        model.addAttribute("courses",courseJsons);
//        return "teacher";
        LoginRequestJson loginRequestJson=new LoginRequestJson();
        loginRequestJson.setEmail(email);
        loginRequestJson.setPassword(password);
        LoginRespJson loginRespJson=infoService.login(loginRequestJson);
        String status=loginRespJson.getStatus();
        if(status.equals("success")){           //登录成功
            model.addAttribute("name",email);
            if(loginRespJson.getIdentity().equals("teacher")){
                List<ExamJson> examJsons=loginRespJson.getExams();
                model.addAttribute("exams",examJsons);
                List<CourseJson> courseJsons=loginRespJson.getCourses();
                model.addAttribute("courses",courseJsons);
                String templatePath="http://localhost:8803/jQuery.js";
                try {
                    DownloadResponse response=examDataService.download();
                    if(response.getStatus().equals("success")){
                        templatePath=response.getPath();
                    }

                }catch (Exception e){
                    templatePath=e.toString();
                    e.printStackTrace();
                }
                model.addAttribute("templatePath",templatePath);
                model.addAttribute("accoutEmail",email);
                return "teacher";
            }else{
                List<ExamJson> examJsons = loginRespJson.getExams();
                model.addAttribute("exams", examJsons);
                model.addAttribute("email",email);
                return "student";
            }
        }else{                                              //登录失败
            model.addAttribute("msg",loginRespJson.getMsg());//返回失败信息，这块界面还没写
            return "index";
        }
    }
    @RequestMapping(value = "/studentRegistPage",method = RequestMethod.GET)
    public String studentRegistPage(){
        return "studentRegist";
    }
    @RequestMapping(value = "/teacherRegistPage",method = RequestMethod.GET)
    public String teacherRegistPage(){
        return "teacherRegist";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
