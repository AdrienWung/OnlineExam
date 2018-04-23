package online.ui.Controller;

import online.ui.Interface.ExamService;
import online.ui.Interface.ScoreService;
import online.ui.bean.onlineexam.QueryExamResponseBean;
import online.ui.bean.onlineexam.StartExamRequestBean;
import online.ui.bean.onlineexam.StartExamResponseBean;
import online.ui.bean.question.QuestionInfoBean;
import online.ui.bean.scoring.*;
import online.ui.util.DateUtil;
import online.ui.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/exam")
@SessionAttributes({"vo","examID", "email"})
public class ExamController {

    @Resource
    private ExamService examService;

    @Resource
    private ScoreService scoreService;

    @RequestMapping(value = "/startExam",method = RequestMethod.POST)
    public String startExam(@RequestParam String password, Model model){
        Map map = model.asMap();
        Integer examID = (Integer) map.get("examID");
        String email = (String) map.get("email");
        StartExamRequestBean req = new StartExamRequestBean();
        req.setExamID(examID);
        req.setEmail(email);
        req.setPassword(password);
        StartExamResponseBean bean = examService.start(req);
        ExamInfoVO vo = new ExamInfoVO();
        List<QuestionInfoBean> single = bean.getSingle();
        List<QuestionInfoBean> multiple = bean.getMultiple();
        int singleNum = 0, multiNum = 0;
        vo.setExamTime(timeBetween(bean.getStartTime(), bean.getEndTime()));
        int singleScore = 0, multiScore = 0;
        for (QuestionInfoBean q:
             single) {
            QuestionVO vo1 = new QuestionVO();
            vo1.setQuestionID(q.getQuestionID());
            vo1.setDescription(q.getDescription());
            vo1.setScore(q.getScore());
            String[] selections = q.getSelections().split(";");
            int j = 1;
            for (String s :
                    selections) {
                SelectionVO vo2 = getSelectionVO(s);
                vo2.setIndex(j++);
                vo1.addSelections(vo2);
            }
            vo1.setIndex(++singleNum);
            vo.addSingle(vo1);
            singleScore += q.getScore();
        }

        for (QuestionInfoBean q:
                multiple) {
            QuestionVO vo1 = new QuestionVO();
            vo1.setQuestionID(q.getQuestionID());
            vo1.setDescription(q.getDescription());
            vo1.setScore(q.getScore());
            String[] selections = q.getSelections().split(";");
            int j = 1;
            for (String s :
                    selections) {
                SelectionVO vo2 = getSelectionVO(s);
                vo2.setIndex(j++);
                vo1.addSelections(vo2);
            }
            vo1.setIndex(++multiNum);
            vo.addMultiple(vo1);
            multiScore += q.getScore();
        }
        vo.setSingleNum(singleNum);
        vo.setMultiNum(multiNum);
        vo.setSingleScore(singleScore);
        vo.setMultiScore(multiScore);
        model.addAttribute("vo", vo);
        return "exam";
    }

    @RequestMapping(value = "/preExam",method = RequestMethod.GET)
    public String preExam(@RequestParam int examID, Model model){
        QueryExamResponseBean bean = examService.query(examID);
        model.addAttribute("examID", examID);
        model.addAttribute("bean", bean);
        return "examInfo";
    }

    @RequestMapping(value = "/commit",method = RequestMethod.POST)
    public String commit(HttpServletRequest request, Model model){
        Map map = model.asMap();
        ExamInfoVO vo = (ExamInfoVO) map.get("vo");
        Integer examID = (Integer) map.get("examID");
        String email = (String) map.get("email");
        List<QuestionVO> single = vo.getSingleList();
        List<QuestionVO> multi = vo.getMultiList();
        ScoringRequestBean bean = new ScoringRequestBean();
        bean.setEmail(email);
        bean.setExamID(examID);
        System.out.println("考生邮箱：" + email + " 考试ID：" + examID);
        List<AnswerRequestBean> answerList = bean.getQuestions();
        for (QuestionVO svo :
                single) {
            AnswerRequestBean answer = new AnswerRequestBean();
            answer.setQuestionID(svo.getQuestionID());
            String sanswer = request.getParameter("single_" + svo.getIndex());
            answer.setPersonalAnswer(sanswer);
            System.out.println("问题ID：" + svo.getQuestionID() + " 问题序号：" + svo.getIndex() + " 考生答案：" + answer.getPersonalAnswer());
            answerList.add(answer);
        }
        for (QuestionVO mvo :
                multi) {
            AnswerRequestBean answer = new AnswerRequestBean();
            answer.setQuestionID(mvo.getQuestionID());
            String manswer = request.getParameter("multi_" + mvo.getIndex());
            answer.setPersonalAnswer(manswer != null ? manswer.replace(",", ";") : "");
            System.out.println("问题ID：" + mvo.getQuestionID() + " 问题序号：" + mvo.getIndex() + " 考生答案：" + answer.getPersonalAnswer());
            answerList.add(answer);
        }
        ScoringResponseBean scoringResp = scoreService.scoring(bean);
        model.addAttribute("scorevo", getScoreVO(scoringResp));
        return "scoreDetail";
    }

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String query(@RequestParam Integer examID, Model model){
        Map map = model.asMap();
        String email = (String) map.get("email");
        ScoringResponseBean scoringResp = scoreService.history(new HistoryRequestBean(email, examID));
        model.addAttribute("scorevo", getScoreVO(scoringResp));
        return "scoreDetail";
    }

    @RequestMapping(value = "/createPaper",method = RequestMethod.GET)
    public String createPaper(@RequestParam Integer examID, @RequestParam String email, Model model){
        ScoringResponseBean scoringResp = scoreService.history(new HistoryRequestBean(email, examID));
        model.addAttribute("scorevo", getScoreVO(scoringResp));
        return "scoreDetail";
    }
    private String timeBetween(String start, String end){
        long startDate = DateUtil.parseDate(start).getTime();
        long endDate = DateUtil.parseDate(end).getTime();
        return DateUtil.longToTimeString(endDate - startDate);
    }

    private SelectionVO getSelectionVO(String s){
        SelectionVO vo2 = new SelectionVO();
        vo2.setMark(s.substring(0, 1));
        vo2.setContent(s);
        return vo2;
    }

    private ScoreVO getScoreVO(ScoringResponseBean bean){
        ScoreVO vo = new ScoreVO();
        vo.setExamID(bean.getExamID());
        vo.setEmail(bean.getEmail());
        vo.setCourse(bean.getCourse());
        vo.setScore(bean.getScore() + "");
        List<AnswerResponseBean> questions = bean.getQuestions();
        int singleNum = 0, multiNum = 0;
        int singleScore = 0, multiScore = 0;
        for (AnswerResponseBean q:
                questions) {
            AnswerVO vo1 = new AnswerVO();
            vo1.setAnswer(q.getAnswer());

            vo1.setDescription(q.getDescription());
            vo1.setScore(bean.getQuestionScore());
            String[] selections = q.getSelections().split(";");
            int j = 1;
            for (String s :
                    selections) {
                AnswerSelectionVO vo2 = getAnswerSelectionVO(s, q.getPersonalAnswer());
                vo2.setIndex(j++);
                vo1.addSelections(vo2);
            }
            if ("multiple".equalsIgnoreCase(q.getCategory())) {
                vo1.setIndex(++multiNum);
                vo.addMultiple(vo1);
                multiScore += bean.getQuestionScore();
            } else {
                vo1.setIndex(++singleNum);
                vo.addSingle(vo1);
                singleScore += bean.getQuestionScore();
            }
        }
        vo.setSingleNum(singleNum);
        vo.setMultiNum(multiNum);
        vo.setSingleScore(singleScore);
        vo.setMultiScore(multiScore);
        return vo;
    }

    private AnswerSelectionVO getAnswerSelectionVO(String s, String choice){
        AnswerSelectionVO vo2 = new AnswerSelectionVO();
        System.out.println("学生答案：" + choice + " 该选项为" + s);
        vo2.setMark(s.substring(0, 1));
        vo2.setContent(s);
        System.out.println("该选项应该为" + choice.contains(s.substring(0, 1)));
        vo2.setChecked(choice.contains(s.substring(0, 1)) ? "true" : "false");
        return vo2;
    }

}
