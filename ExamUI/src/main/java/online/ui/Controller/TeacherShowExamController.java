package online.ui.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import online.ui.Interface.ExamService;
import online.ui.Interface.ScoreService;
import online.ui.Json.ScoreStateRequest;
import online.ui.Json.ScoreStateResponse;
import online.ui.Json.info.ParticipantsJson;
import online.ui.bean.onlineexam.QueryExamResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherShowExamController {

    @Autowired
    private ExamService examService;


    @Autowired
    private ScoreService scoreService;

    @RequestMapping(value = "/showExam",method = RequestMethod.POST)
    public String showExam(@RequestParam String examID, Model model){
        QueryExamResponseBean responseBean=examService.query(Integer.parseInt(examID));
        List<ParticipantsJson> jsonList=responseBean.getParticipants();
        ScoreStateRequest request=new ScoreStateRequest();
        request.setEmails(jsonList);
        request.setExamID(examID);
        String answer= scoreService.getScoreState(request);
        JSONArray array= JSONArray.parseArray(answer);
        List<ScoreStateResponse> states=new ArrayList<>();
        for(int i=0;i<array.size();i++){
            JSONObject object=array.getJSONObject(i);
            ScoreStateResponse scoreStateResponse=new ScoreStateResponse();
            scoreStateResponse.setEmail(object.get("email").toString());
            scoreStateResponse.setScore(object.get("score").toString());
            scoreStateResponse.setState(object.get("state").toString());
            states.add(scoreStateResponse);
        }
        model.addAttribute("scoreStates",states);
        model.addAttribute("examID",examID);
        return "teacherShowExam";
    }
}
