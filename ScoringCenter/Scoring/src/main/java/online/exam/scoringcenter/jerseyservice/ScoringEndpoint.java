package online.exam.scoringcenter.jerseyservice;


import io.swagger.annotations.*;
import online.exam.scoringcenter.exception.ErrorJson;
import online.exam.scoringcenter.exception.OperationException;
import online.exam.scoringcenter.model.request.*;
import online.exam.scoringcenter.model.response.ExamScoreResponse;
import online.exam.scoringcenter.model.response.ExamStateResponse;
import online.exam.scoringcenter.model.response.QueryResponse;
import online.exam.scoringcenter.model.response.ScoringResponse;
import online.exam.scoringcenter.service.ScoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableFeignClients
@Path("/scoring")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Scoring API", produces = "application/json")
public class ScoringEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoringEndpoint.class);

    @Autowired
    private ScoringService scoringService;

    @POST
    @Path("/")
    @ApiOperation(
            value = "按照學生信息查找歷史成績")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = QueryResponse.class),
            @ApiResponse(code = 404, message = "资源錯誤")
    })
    public Response query(@ApiParam Scoring scoring) {
        LOGGER.info("scoring/scoring{} - {}", scoring, MediaType.APPLICATION_JSON);

        try {
            ScoringResponse scoringResponse = scoringService.scoring(scoring);
            return Response.status(Response.Status.OK).entity(scoringResponse).build();
        } catch (OperationException e) {
            ErrorJson error = new ErrorJson("Failed", e.getMsg());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @POST
    @Path("/query")
    @ApiOperation(
            value = "按照學生信息查找歷史成績")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = QueryResponse.class),
            @ApiResponse(code = 404, message = "资源錯誤")
    })
    public Response query(@ApiParam Query query) {
        LOGGER.info("scoring/query{} - {}", query, MediaType.APPLICATION_JSON);

        List<QueryResponse> list = null;
        try {
            //should not change list value
            list = scoringService.queryByEmail(query.getEmail());
        } catch (OperationException e) {
            ErrorJson error = new ErrorJson("Failed", e.getMsg());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
        return Response.status(Response.Status.OK).entity(list).build();
    }

    @POST
    @Path("/history")
    @ApiOperation(
            value = "按照學生信息查找指定考試")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ScoringResponse.class),
            @ApiResponse(code = 404, message = "资源錯誤")
    })
    public Response history(@ApiParam History history) {
        LOGGER.info("scoring/history{} - {}", history, MediaType.APPLICATION_JSON);

        try {
            ScoringResponse scoringResponse = scoringService.queryHistory(history.getEmail(), history.getExamID());
            return Response.status(Response.Status.OK).entity(scoringResponse).build();
        } catch (OperationException e) {
            ErrorJson error = new ErrorJson("Failed", e.getMsg());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @POST
    @Path("/exam/state")
    @ApiOperation(
            value = "查询考试状态")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ExamStateResponse.class),
            @ApiResponse(code = 404, message = "资源錯誤")
    })
    public Response examState(@ApiParam History history) {
        LOGGER.info("scoring/exam/state{} - {}", history, MediaType.APPLICATION_JSON);

        try {
            ExamStateResponse examStateResponse = scoringService.examState(history.getEmail(), history.getExamID());
            return Response.status(Response.Status.OK).entity(examStateResponse).build();
        } catch (OperationException e) {
            ErrorJson error = new ErrorJson("Failed", e.getMsg());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @POST
    @Path("/exam/scoreState")
    @ApiOperation(
            value = "查询考试状态批量")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ExamScoreResponse.class),
            @ApiResponse(code = 404, message = "资源錯誤")
    })
    public Response examStates(@ApiParam States states) {
        LOGGER.info("scoring/scoreState{} - {}", states, MediaType.APPLICATION_JSON);

        try {
            List<ExamScoreResponse> examScoreResponses = new ArrayList<ExamScoreResponse>();
            for (SingleEmail singleEmail : states.getEmails()) {
                ExamStateResponse examStateResponse = scoringService.examState(singleEmail.getEmail(), states.getExamID());
                int score = 0;
                try {
                    ScoringResponse scoring  = scoringService.queryHistory(singleEmail.getEmail(), states.getExamID());
                    score = scoring.getScore();
                } catch (OperationException e) {
                    score = 0;
                }
                ExamScoreResponse response = new ExamScoreResponse();
                response.setEmail(examStateResponse.getEmail());
                response.setScore(score);
                response.setState(examStateResponse.getState());
                examScoreResponses.add(response);
            }
            return Response.status(Response.Status.OK).entity(examScoreResponses).build();
        } catch (OperationException e) {
            ErrorJson error = new ErrorJson("Failed", e.getMsg());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }
}
