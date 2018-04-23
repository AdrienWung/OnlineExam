package online.ui.Interface;

import online.ui.Json.LoadStudentListResponse;
import online.ui.Json.online.CreateExamRequest;
import online.ui.Json.online.CreateExamResponse;
import online.ui.bean.onlineexam.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "ONLINETEST")
public interface ExamService {

    @RequestMapping(value = "/onlineexam/create",method = RequestMethod.POST)
    CreateExamResponse create(@RequestBody CreateExamRequest request);

    @RequestMapping(value = "/onlineexam/exam/{examID}",method = RequestMethod.GET)
    QueryExamResponseBean query(@PathVariable("examID") Integer examId);

    @RequestMapping(value = "/onlineexam/start",method = RequestMethod.POST)
    StartExamResponseBean start(@RequestBody StartExamRequestBean request);

    @RequestMapping(value = "/onlineexam/loadStudentList",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    LoadStudentListResponse loadStudentList(@RequestPart(value = "file") MultipartFile file);
}
