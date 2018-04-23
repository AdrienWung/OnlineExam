package online.ui.Controller;

import online.ui.Interface.ExamDataService;
import online.ui.Interface.ExamService;
import online.ui.Interface.InfoService;
import online.ui.Json.DownloadResponse;
import online.ui.Json.EmailJson;
import online.ui.Json.GeneralResponse;
import online.ui.Json.LoadStudentListResponse;
import online.ui.Json.info.NewCourseJson;
import online.ui.Json.info.ParticipantsJson;
import online.ui.Json.online.CreateExamRequest;
import online.ui.Json.online.CreateExamResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    ExamService examService;
    @Autowired
    InfoService infoService;
    @Autowired
    ExamDataService examDataService;


    @RequestMapping(value = "/createExam",method = RequestMethod.POST)
    public String createExam(@RequestParam String questionNum,@RequestParam String questionScore,@RequestParam String startTime, @RequestParam String endTime,
                             @RequestParam String examName,@RequestParam String course,@RequestParam String groupBy,@RequestParam String emails){
        CreateExamRequest request=new CreateExamRequest();
        request.setCourse(course);
        request.setEndTime(endTime);
        request.setStartTime(startTime);
        request.setExamName(examName);
        request.setGroupBy(groupBy);
        List<ParticipantsJson> jsonList=new ArrayList<>();
        String[] strings= emails.split(",");
        for(int i=0;i<strings.length;i++){
            ParticipantsJson participantsJson=new ParticipantsJson();
            participantsJson.setEmail(strings[i]);
            jsonList.add(participantsJson);
        }
        request.setParticipants(jsonList);
        request.setQuestionNum(questionNum);
        request.setQuestionScore(questionScore);
        CreateExamResponse response;
        try{
            response=examService.create(request);


        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
        if(response.getStatus().equals("success")){
            try {
                for (int i = 0; i < strings.length; i++) {
                    EmailJson json=new EmailJson();
                    json.setContent(response.getPassword());
                    json.setEmail(strings[i]);
                    json.setTitle("考试密码");
                    infoService.sendEmail(json);
                }
                return "生成考试成功";
            }catch (Exception e){
                return e.toString();
            }
        }else{
            return response.getExamName();
        }
    }
    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    public String addCourse(@RequestParam String course,@RequestParam String email){
        NewCourseJson json=new NewCourseJson();
        json.setCourse(course);
        json.setEmail(email);
        GeneralResponse response=infoService.newCourse(json);
        if(response.getStatus().equals("success")){
            return "success";
        }else{
            return response.getMessage();
        }
    }

    @RequestMapping(value="/uploadQuestions/{course}",method = RequestMethod.POST)
    public String uploadQuestions(@PathVariable("course") String course,@RequestParam("file") MultipartFile file){
        try{
            DownloadResponse response=examDataService.upload(course,file);
            if(response.getStatus().equals("success")){
                return "success";
            }else{
                return "fail";
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }

    @RequestMapping(value="/uploadStudents",method = RequestMethod.POST)
    public List<String> uploadStudents(@RequestParam("file") MultipartFile file){
        List<String> result=new ArrayList<>();
        try{
            //测试用
//            List<ParticipantsJson> participantsJsons=new ArrayList<>();
//            ParticipantsJson json=new ParticipantsJson();
//            json.setEmail("123@163.com");
//            participantsJsons.add(json);
//            json=new ParticipantsJson();
//            json.setEmail("456@163.com");
//            participantsJsons.add(json);
//            //
//
//            for(int i=0;i<participantsJsons.size();i++){
//                result.add(participantsJsons.get(i).getEmail());
//            }
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while((line=bufferedReader.readLine())!=null){
                if(!line.trim().isEmpty()) {
                    result.add(line.trim());
                }
            }
//            LoadStudentListResponse response=examService.loadStudentList(file);
//            if(response.getStatus().equals("success")){
//                List<ParticipantsJson> jsonList=response.getParticipants();
//                List<String> responseList=new ArrayList<>();
//                for(int i=0;i<jsonList.size();i++){
//                    responseList.add(jsonList.get(i).getEmail());
//                }
//                return responseList;
//            }else{
//                return new ArrayList<>();
//            }
        }catch (Exception e){
            e.printStackTrace();
            result.add(e.toString());
        }
        return result;
    }

    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
    public void testDownload(HttpServletResponse res) {
        String fileName = "students.txt";
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }
}