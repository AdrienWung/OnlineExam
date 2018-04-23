package online.exam.info;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import com.netflix.discovery.converters.wrappers.CodecWrappers;
import online.exam.info.JsonObjects.*;
import online.exam.info.Service.EmailService;
import online.exam.info.Service.OnlineExamService;
import online.exam.info.Service.ScoringService;
import online.exam.info.Service.VerCodeService;
import online.exam.info.dao.CourseInfoDao;
import online.exam.info.dao.StudentInfoDao;
import online.exam.info.dao.TeacherInfoDao;
import online.exam.info.entity.CourseInfo;
import online.exam.info.entity.StudentInfo;
import online.exam.info.entity.TeacherInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RestController
@MapperScan("online.email.info")
public class InfoApplication {
	@Autowired
	private EmailService emailService;

	@Autowired
	private VerCodeService verCodeService;

	@Autowired
	private StudentInfoDao studentInfoDao;

	@Autowired
	private TeacherInfoDao teacherInfoDao;

	@Autowired
	private CourseInfoDao courseInfoDao;

	@Autowired
	private ScoringService scoringService;

	@Autowired
	private OnlineExamService onlineExamService;


	public static void main(String[] args) {
		SpringApplication.run(InfoApplication.class, args);
	}

	@RequestMapping(value="/register/sendVerCode",method= RequestMethod.POST)
	public GeneralResponse getVerCode(@RequestBody SimpleEmailJson emailJson){
		return verCodeService.getVercode(emailJson.getEmail());
	}

	@RequestMapping(value = "/regist/student",method = RequestMethod.POST)
	@ResponseBody
	public GeneralResponse regist(@RequestBody StudentRegistJson json){
		boolean result=verCodeService.activateVercode(json.getEmail(),json.getVerCode());
		GeneralResponse  response=new GeneralResponse();
		if(!result){
			response.setStatus("fail");
			response.setMessage("验证码错误");
			return response;
		}
		StudentInfo info=new StudentInfo();
		info.setEmail(json.getEmail());
		info.setPassword(json.getPassword());
		info.setName(json.getName());
		info.setClassName(json.getClassName());
		info.setGrade(json.getGradeName());

		try {
			studentInfoDao.insert(info);
		}catch (Exception e){
			response.setStatus("fail");
			response.setMessage("邮箱已注册");
			return response;
		}
		response.setStatus("success");
		response.setMessage("注册成功");
		return response;
	}

	@RequestMapping(value = "/regist/teacher",method = RequestMethod.POST)
	@ResponseBody
	public GeneralResponse teacherRegist(@RequestBody TeacherRegistJson json){
		boolean result=verCodeService.activateVercode(json.getEmail(),json.getVerCode());
		GeneralResponse  response=new GeneralResponse();
		if(!result){
			response.setStatus("fail");
			response.setMessage("验证码错误");
			return response;
		}
		TeacherInfo info=new TeacherInfo();
		info.setEmail(json.getEmail());
		info.setPassword(json.getPassword());
		info.setName(json.getName());
		try {
			teacherInfoDao.insert(info);
		}catch (Exception e){
			response.setStatus("fail");
			response.setMessage("邮箱已注册");
			return response;
		}
		response.setStatus("success");
		response.setMessage("注册成功");
		return response;
	}

	@RequestMapping(value="/login",method = RequestMethod.POST)
	@ResponseBody
	public LoginRespJson login(@RequestBody LoginJson json) {
		LoginRespJson respJson=new LoginRespJson();
		List<CourseJson> courses=new ArrayList<>();
		TeacherInfo teacherInfo=teacherInfoDao.getFromEmailAndPassword(json.getEmail(),json.getPassword());
		if(teacherInfo==null){
			StudentInfo info=studentInfoDao.getFromEmailAndPassword(json.getEmail(),json.getPassword());
			if(info==null){
				respJson.setStatus("fail");
				respJson.setMsg("账号或密码错误");
			}else{
				respJson.setStatus("success");
				respJson.setIdentity("student");
				respJson.setMsg("登录成功");
			}
		}else{
			List<CourseInfo> courseInfos=courseInfoDao.getCourseInfo(teacherInfo.getEmail());
			for(int i=0;i<courseInfos.size();i++) {
				CourseJson courseJson=new CourseJson();
				courseJson.setCourseName(courseInfos.get(i).getName());
				courses.add(courseJson);
			}
			respJson.setStatus("success");
			respJson.setIdentity("teacher");
			respJson.setMsg("登陆成功");
		}
		respJson.setCourses(courses);
		List<ExamJson> examJsons=new ArrayList<>();
		respJson.setExams(examJsons);
		//查询考试信息
		try{
			SimpleEmailJson simpleEmailJson=new SimpleEmailJson();
			simpleEmailJson.setEmail(json.getEmail());

			String response=onlineExamService.query(simpleEmailJson);
			JSONArray array=new JSONArray(response);
			for(int i=0;i<array.length();i++){
				JSONObject object=array.getJSONObject(i);
				EmailAndExamJson emailAndExamJson=new EmailAndExamJson();
				emailAndExamJson.setEmail(json.getEmail());
				emailAndExamJson.setExamID(object.get("examID").toString());
				ExamJson examJson=new ExamJson();
				examJson.setExamID(object.get("examID").toString());
				examJson.setExamName(object.get("examName").toString());
				examJson.setStatus(scoringService.query(emailAndExamJson).getState());
				examJsons.add(examJson);
			}
			respJson.setExams(examJsons);
		}catch (Exception e){
			e.printStackTrace();
		}
		return respJson;
	}

	@RequestMapping(value="/newCourse",method = RequestMethod.POST)
	@ResponseBody
	public GeneralResponse newCourse(@RequestBody NewCourseJson json) {
		GeneralResponse generalResponse=new GeneralResponse();
		CourseInfo info=new CourseInfo();
		info.setEmail(json.getEmail());
		info.setName(json.getCourse());
		try {
			courseInfoDao.insert(info);
		}catch (Exception e){
			generalResponse.setStatus("fail");
			generalResponse.setMessage("添加失败");
			return  generalResponse;
		}
		generalResponse.setStatus("success");
		generalResponse.setMessage("添加成功");
		return generalResponse;
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
