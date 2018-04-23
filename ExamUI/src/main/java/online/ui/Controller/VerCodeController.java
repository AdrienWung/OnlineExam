package online.ui.Controller;

import online.ui.Interface.InfoService;
import online.ui.Json.GeneralResponse;
import online.ui.Json.info.SimpleEmailJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerCodeController {

    @Autowired
    InfoService infoService;

    @RequestMapping(value = "/verCode",method = RequestMethod.POST)
    public GeneralResponse sendVerCode(@RequestParam String email){
        SimpleEmailJson json=new SimpleEmailJson();
        json.setEmail(email);
        GeneralResponse response=infoService.getVerCode(json);
        return response;
    }
}
