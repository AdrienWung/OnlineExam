package online.ui.Controller;

import online.ui.Interface.InfoService;
import online.ui.Json.GeneralResponse;
import online.ui.Json.info.StudentRegistJson;
import online.ui.Json.info.TeacherRegistJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistController {
    @Autowired
    InfoService infoService;
    @RequestMapping(value = "/studentRegist",method = RequestMethod.POST)
    public GeneralResponse studentRegist(@RequestParam String email,@RequestParam String name,@RequestParam String password,
                                @RequestParam String className,@RequestParam String grade,@RequestParam String verCode){
        StudentRegistJson json=new StudentRegistJson();
        json.setClassName(className);
        json.setEmail(email);
        json.setGradeName(grade);
        json.setName(name);
        json.setPassword(password);
        json.setVerCode(verCode);
        GeneralResponse response=infoService.studentRegist(json);
        return response;

    }
    @RequestMapping(value = "/teacherRegist",method = RequestMethod.POST)
    public GeneralResponse teacherRegist(@RequestParam String email,@RequestParam String password,@RequestParam String name
                                ,@RequestParam String verCode){
        TeacherRegistJson json=new TeacherRegistJson();
        json.setEmail(email);
        json.setName(name);
        json.setPassword(password);
        json.setVerCode(verCode);
        GeneralResponse response=infoService.teacherRegist(json);
        return response;

    }




}
