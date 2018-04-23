package online.exam.datacenter.jerseyservice;

import io.swagger.annotations.*;
import online.exam.datacenter.exception.ErrorJson;
import online.exam.datacenter.exception.OperationException;
import online.exam.datacenter.model.Question;
import online.exam.datacenter.model.RandomInfo;
import online.exam.datacenter.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/question")
@EnableFeignClients
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Exam Question API", produces = "application/json")
public class QuestionEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionEndpoint.class);

    @Autowired
    private QuestionService questionService;

    @GET
    @Path("/{questionID}")
    @ApiOperation(
            value = "提供一个問題ID",
            response = Question.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = Question.class),
            @ApiResponse(code = 404, message = "找不到资源")
    })
    public Response findQuestionByGet(@ApiParam @PathParam("questionID") String questionID) {
        LOGGER.info("question/{} - {}", questionID, MediaType.APPLICATION_JSON);
        return this.constructQuestionResponse(questionID);
    }

    @POST
    @Path("/random")
    @ApiOperation(
            value = "选取随机试题")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = RandomInfo.class),
            @ApiResponse(code = 404, message = "资源錯誤")
    })
    public Response randomQuestions(@ApiParam RandomInfo randomInfo) {
        LOGGER.info("question/random{} - {}", randomInfo, MediaType.APPLICATION_JSON);

        List<Question> list = null;
        try {
            //should not change list value
            list = questionService.randomQuestions(randomInfo);
        } catch (OperationException e) {
            ErrorJson error = new ErrorJson("Failed", e.getMsg());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
        return Response.status(Response.Status.OK).entity(list).build();
    }

    private Response constructQuestionResponse(String questionID) {
        if ("404".equals(questionID)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Question result = questionService.findByQuestionID(questionID);
        if (result == null) {
            ErrorJson error = new ErrorJson("Failed", "No such Question");
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
        return Response.status(Response.Status.OK).entity(result).build();
    }
}
