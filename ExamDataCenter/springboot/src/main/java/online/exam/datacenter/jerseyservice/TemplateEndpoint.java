package online.exam.datacenter.jerseyservice;

import io.swagger.annotations.*;
import online.exam.datacenter.service.TemplateService;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by SK on 2017/11/30.
 */

@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/template")
@Api(value = "Excel Template Download API", produces = "application/json")
public class TemplateEndpoint {

    @Autowired
    TemplateService templateService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateEndpoint.class);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/download")
    @ApiOperation(
            value = "下载试题模板"
            )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = EndpointResponse.class),
            @ApiResponse(code = 404, message = "找不到资源")
    })
    public Response downloadTemplate() {
    	EndpointResponse response = new EndpointResponse("success", "http://114.115.137.64:8081/template.xlsx");
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @POST
    @Path("/upload/{course}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @ApiOperation(value = "上传试题")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = EndpointResponse.class),
            @ApiResponse(code = 500, message = "资源錯誤")
    })
	public Response uploadFile(@ApiParam FormDataMultiPart form, @ApiParam @PathParam("course") String course) {

        try {
            FormDataBodyPart filePart = form.getField("file");
            templateService.importFile(filePart.getValueAs(InputStream.class), course);
        } catch (IOException e) {
            e.printStackTrace();
            EndpointResponse response = new EndpointResponse("fail", e.getMessage());
            return Response.status(Response.Status.OK).entity(response).build();
        }

        EndpointResponse response = new EndpointResponse("success","");
		return Response.status(Response.Status.OK).entity(response).build();

	}



}
