package online.email;
import online.email.JsonObjects.EmailJson;
import online.email.JsonObjects.GeneralResponse;
import online.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EmailApplication {

	@Autowired
	private EmailService emailService;


	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
	}


	@RequestMapping(value="/sendEmail",method=RequestMethod.POST)
	@ResponseBody
	public GeneralResponse sendEmail(@RequestBody EmailJson json) {
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


}
