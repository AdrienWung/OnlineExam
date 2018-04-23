package online.exam.scoringcenter.service;

import online.exam.scoringcenter.exception.OperationException;
import online.exam.scoringcenter.model.request.Scoring;
import online.exam.scoringcenter.model.response.ExamStateResponse;
import online.exam.scoringcenter.model.response.QueryResponse;
import online.exam.scoringcenter.model.response.ScoringResponse;

import java.util.List;

public interface ScoringService {

    List<QueryResponse> queryByEmail(String email) throws OperationException;

    ScoringResponse queryHistory(String email, int examID) throws OperationException;

    ScoringResponse scoring(Scoring scoring) throws OperationException;

    ExamStateResponse examState(String email, int examID) throws OperationException;
}
