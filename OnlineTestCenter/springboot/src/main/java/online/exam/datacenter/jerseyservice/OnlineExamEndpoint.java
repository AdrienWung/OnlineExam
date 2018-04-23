package online.exam.datacenter.jerseyservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import online.exam.datacenter.VO.EmailRequest;
import online.exam.datacenter.VO.ExamRequest;
import online.exam.datacenter.VO.ExamResponse;
import online.exam.datacenter.VO.QuestionVO;
import online.exam.datacenter.VO.RandomInfo;
import online.exam.datacenter.exception.OperationException;
import online.exam.datacenter.model.Exam;
import online.exam.datacenter.model.Question;
import online.exam.datacenter.model.Student;

import online.exam.datacenter.service.OnlineExamService;
import online.exam.datacenter.service.QuestionService;

@Component
@EnableFeignClients
@Path("/onlineexam")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Online Exam API", produces = "application/json")
public class OnlineExamEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(OnlineExamEndpoint.class);

    @Autowired
    OnlineExamService onlineExamService;

    @Autowired
    QuestionService questionService;
    

        
    
    
    @POST
    @Path("/loadStudentList")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @ApiOperation(
            value = "上传学生名单")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ExamResponse.class),
            @ApiResponse(code = 404, message = "资源錯誤")
    })
	public Response uploadFile(@ApiParam FormDataMultiPart form
			) {

        Map<String, Object> response = new HashMap<String, Object>();
        try {
        	FormDataBodyPart filePart = form.getField("file");
            List<Student> studentList = onlineExamService.importFile(filePart.getValueAs(InputStream.class));
            response.put("status", "success");
            response.put("participants", studentList);
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (IOException e) {
            e.printStackTrace();
            response.put("status", "fail");
            response.put("errMsg", e.getMessage());
            return Response.status(Response.Status.OK).entity(response).build();
        }

	}

    


    @GET
    @Path("/exam/{examID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "查看某一考试基本信息（无试题）"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ExamResponse.class),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public Response getExam(@ApiParam @PathParam("examID") int examID) {

        Map<String, Object> response = new HashMap<String, Object>();
        try{
        	Exam exam = onlineExamService.getExam(examID);
	        response.put("status", "success");
	        response.put("examName", exam.getExamName());
	        response.put("course", exam.getCourse());
	        response.put("questionNum", exam.getQuestionNum());
	        response.put("questionScore", exam.getQuestionScore());
	        response.put("startTime", exam.getStartTime());
	        response.put("endTime", exam.getEndTime());
	        System.out.println(exam.getParticipants()+"\n\n");
	        response.put("participants", exam.getParticipants());
	        return Response.status(Response.Status.OK).entity(new Gson().toJson(response)).build();
        }catch(Exception e){
            response.put("status", "fail");
            response.put("errMsg", e.getMessage());
            return Response.status(Response.Status.OK).entity(response).build();
        }
        
    }


    @POST
    @Path("/start")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "考生开始考试")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ExamResponse.class),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public Response startExam(
            @ApiParam ExamRequest request) {

        try {
            Map<String, Object> response = new HashMap<>();
            Exam exam = onlineExamService.startExam(request);
            response.put("status", "success");
            response.put("startTime", exam.getStartTime());
            response.put("endTime", exam.getEndTime());
            List<Question> questions = questionService.findRandomQuestion(new RandomInfo(exam.getQuestionNum(), exam.getCourse()));
            List<QuestionVO> singles = new ArrayList<QuestionVO>();
            List<QuestionVO> multiples = new ArrayList<QuestionVO>();
            
            for(int i=0;i<questions.size();i++){
            	Question tmpQuestion = questions.get(i);
            	if(questions.get(i).getCategory().trim().equals("single")){
            		singles.add(new QuestionVO(tmpQuestion.getQuestionID(),tmpQuestion.getCategory(),tmpQuestion.getDescription(),tmpQuestion.getSelections(),exam.getQuestionScore()));
            	}else{
            		multiples.add(new QuestionVO(tmpQuestion.getQuestionID(),tmpQuestion.getCategory(),tmpQuestion.getDescription(),tmpQuestion.getSelections(),exam.getQuestionScore()));
                	
            	}
            }
            
            response.put("single", singles);
            response.put("multiple",multiples);
            
            return Response.status(Response.Status.OK).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "fail");
            if (e instanceof OperationException){
                response.put("errMsg", ((OperationException)e).getMsg());
            }else {
                response.put("errMsg", e.getMessage());
            }
            return Response.status(Response.Status.OK).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "老师新生成考试")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ExamResponse.class),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public Response createExam(@ApiParam Exam exam) {
    	try{
    		ExamResponse response = onlineExamService.createExam(exam);

    		return Response.status(Response.Status.OK).entity(new Gson().toJson(response)).build();
        }catch(Exception e){
//        	Map<String, Object> response = new HashMap<>();
        	ExamResponse response = new ExamResponse();
        	response.setExamID(-1);
        	response.setStatus("fail");
        	response.setExamName(e.getMessage());
            return Response.status(Response.Status.OK).entity(response).build();
        }
    	
    }

    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "查询考试")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ExamResponse.class),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public Response query(@ApiParam Student student) {
        List<Exam> exams = onlineExamService.getExamByStudent(student);

        List<ExamResponse> examResponses = exams.stream().map(exam -> {
            ExamResponse response = new ExamResponse();
            response.setStatus("success");
            response.setExamID(exam.getId());
            response.setExamName(exam.getExamName());
            response.setPassword(exam.getPassword());
            return response;
        }).collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(new Gson().toJson(examResponses)).build();
    }
}
