package online.exam.scoringcenter.service.impl;

import online.exam.scoringcenter.exception.OperationException;
import online.exam.scoringcenter.mapper.ScoringMapper;
import online.exam.scoringcenter.model.cloud.ExamResponse;
import online.exam.scoringcenter.model.cloud.QuestionResponse;
import online.exam.scoringcenter.model.request.PersonalAnswer;
import online.exam.scoringcenter.model.request.Scoring;
import online.exam.scoringcenter.model.response.ExamStateResponse;
import online.exam.scoringcenter.model.response.QueryResponse;
import online.exam.scoringcenter.model.response.Question;
import online.exam.scoringcenter.model.response.ScoringResponse;
import online.exam.scoringcenter.model.sql.Detail;
import online.exam.scoringcenter.model.sql.Paper;
import online.exam.scoringcenter.service.ScoringService;
import online.exam.scoringcenter.service.cloud.ExamService;
import online.exam.scoringcenter.service.cloud.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScoringServiceImpl implements ScoringService {

    @Autowired
    private ExamService examService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ScoringMapper scoringMapper;

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //inExam, overdue, finished, waiting
    private static final String IN_EXAM = "inExam";
    private static final String OVERDUE = "overdue";
    private static final String FINISHED = "finished";
    private static final String WAITING = "waiting";

    @Override
    @Transactional(readOnly = true)
    public List<QueryResponse> queryByEmail(String email) throws OperationException {
        List<QueryResponse> queryResponseList = new ArrayList<>();
        List<Paper> papers = scoringMapper.findPaperByEmail(email);
        for (Paper paper : papers) {
            QueryResponse response = new QueryResponse();
            response.setExamID(String.valueOf(paper.getExamID()));
            ExamResponse exam = examService.query(String.valueOf(paper.getExamID()));
            if (exam == null) {
                throw new OperationException("No such exam " + paper.getExamID());
            }
            response.setExamName(exam.getExamName());

            queryResponseList.add(response);
        }
        return queryResponseList;
    }

    @Override
    @Transactional(readOnly = true)
    public ScoringResponse queryHistory(String email, int examID) throws OperationException {
        ScoringResponse scoringResponse = new ScoringResponse();
        Paper paper = scoringMapper.findPaper(email, examID);
        if (paper == null) {
            throw new OperationException("No such history ");
        }
        ExamResponse exam = examService.query(String.valueOf(paper.getExamID()));
        if (exam == null) {
            throw new OperationException("No such exam " + paper.getExamID());
        }

        scoringResponse.setEmail(email);
        scoringResponse.setExamID(examID);
        scoringResponse.setCourse(exam.getCourse());
        scoringResponse.setQuestionScore(exam.getQuestionScore());
        scoringResponse.setScore(paper.getScore());
        List<Question> questions = new ArrayList<>();
        //initial questions list
        List<Detail> details = scoringMapper.findDetail(String.valueOf(paper.getPaperID()));
        for (Detail detail : details) {
            Question question = new Question();
            QuestionResponse questionResponse = questionService.query(String.valueOf(detail.getQuestionID()));
            if (questionResponse == null) {
                throw new OperationException("No such question " + detail.getQuestionID());
            }
            question.setAnswer(questionResponse.getAnswer());
            question.setDescription(questionResponse.getDescription());
            question.setPersonalAnswer(detail.getPersonalAnswer());
            question.setQuestionID(detail.getQuestionID());
            question.setSelections(questionResponse.getSelections());
            question.setCategory(questionResponse.getCategory());

            questions.add(question);
        }
        scoringResponse.setQuestions(questions);
        return scoringResponse;
    }

    @Override
    public ScoringResponse scoring(Scoring scoring) throws OperationException {
        ScoringResponse scoringResponse = new ScoringResponse();
        ExamResponse exam = examService.query(String.valueOf(scoring.getExamID()));
        if (exam == null) {
            throw new OperationException("No such exam " + scoring.getExamID());
        }

        scoringResponse.setEmail(scoring.getEmail());
        scoringResponse.setExamID(scoring.getExamID());
        scoringResponse.setCourse(exam.getCourse());
        scoringResponse.setQuestionScore(exam.getQuestionScore());

        int eachScore = Integer.parseInt(exam.getQuestionScore());
        int totalScore = 0;

        List<Question> questions = new ArrayList<>();
        for (PersonalAnswer personalAnswer : scoring.getQuestions()) {
            Question question = new Question();
            QuestionResponse questionResponse = questionService.query(String.valueOf(personalAnswer.getQuestionID()));
            if (questionResponse == null) {
                throw new OperationException("No such question " + personalAnswer.getQuestionID());
            }
            question.setPersonalAnswer(personalAnswer.getPersonalAnswer());
            question.setQuestionID(personalAnswer.getQuestionID());
            question.setAnswer(questionResponse.getAnswer());
            question.setDescription(questionResponse.getDescription());
            question.setSelections(questionResponse.getSelections());
            question.setCategory(questionResponse.getCategory());

            if (question.getPersonalAnswer().equals(question.getAnswer())) {
                totalScore += eachScore;
            }
            questions.add(question);
        }
        scoringResponse.setQuestions(questions);
        scoringResponse.setScore(totalScore);

        //write data
        scoringMapper.insertPaper(scoring.getEmail(), scoring.getExamID(), totalScore);
        Paper paper = scoringMapper.findPaper(scoring.getEmail(), scoring.getExamID());
        for (Question question : questions) {
            scoringMapper.insertDetail(paper.getPaperID(), question.getQuestionID(), question.getPersonalAnswer());
        }
        return scoringResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public ExamStateResponse examState(String email, int examID) throws OperationException {
        ExamStateResponse response = new ExamStateResponse();
        response.setEmail(email);
        response.setExamID(examID);
        Paper paper = scoringMapper.findPaper(email, examID);
        if (paper != null) {
            response.setState(FINISHED);
            return response;
        } else {
            ExamResponse exam = examService.query(String.valueOf(examID));
            if (exam == null) {
                throw new OperationException("No such exam " + examID);
            }

            Date startTime = null;
            Date endTime = null;
            try {
                startTime = formatter.parse(exam.getStartTime());
                endTime = formatter.parse(exam.getEndTime());
            } catch (ParseException e) {
                throw new OperationException("Info error - " + examID);
            }
            Date currentTime = new Date();
            if (currentTime.getTime() < startTime.getTime()) {
                response.setState(WAITING);
            } else if (currentTime.getTime() > endTime.getTime()) {
                response.setState(OVERDUE);
            } else {
                response.setState(IN_EXAM);
            }
        }
        return response;
    }
}