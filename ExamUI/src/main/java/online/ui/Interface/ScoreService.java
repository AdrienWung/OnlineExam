package online.ui.Interface;

import online.ui.Json.ExamJson;
import online.ui.Json.ScoreStateRequest;
import online.ui.Json.ScoreStateResponse;
import online.ui.bean.scoring.HistoryRequestBean;
import online.ui.bean.scoring.ScoringRequestBean;
import online.ui.bean.scoring.ScoringResponseBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SCORING")
public interface ScoreService {
    @RequestMapping(value = "/scoring",method = RequestMethod.POST)
    ScoringResponseBean scoring(@RequestBody ScoringRequestBean request);

    @RequestMapping(value = "/scoring/history",method = RequestMethod.POST)
    ScoringResponseBean history(@RequestBody HistoryRequestBean request);

    @RequestMapping(value="/scoring/exam/scoreState",method = RequestMethod.POST)
    String getScoreState(@RequestBody ScoreStateRequest request);
}
