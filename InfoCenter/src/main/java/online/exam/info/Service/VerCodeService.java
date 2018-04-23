package online.exam.info.Service;

import online.exam.info.JsonObjects.EmailJson;
import online.exam.info.JsonObjects.GeneralResponse;
import online.exam.info.dao.VerCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;
@Component
@EnableFeignClients
public class VerCodeService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private VerCodeDao verCodeDao;

    public GeneralResponse getVercode(String email){
        String num = "";
        for (int i = 0 ; i < 6 ; i ++) {
            num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
        }
        int count=verCodeDao.hasVercode(email);
        System.out.println(count);
        if(count>0){
            verCodeDao.update(email,num);
        }else{
            verCodeDao.insert(email,num);
        }
        EmailJson json=new EmailJson();
        json.setTitle("验证码");
        json.setContent(num);
        json.setEmail(email);
        boolean answer = emailService.sendmessage(json.getEmail(), json.getTitle(), json.getContent());
        GeneralResponse response = new GeneralResponse();
        if (answer) {
            response.setStatus("success");
            response.setMessage("发送成功");
        }else {
            response.setStatus("fail");
            response.setMessage("发送失败");
        }
        return response;
    }
    public boolean activateVercode(String email,String vercode){
        return vercode.equals(verCodeDao.getVercode(email));
    }
}
