package online.ui.Interface;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import online.ui.Json.DownloadResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "EXAMDATA")
public interface ExamDataService {

    @RequestMapping(value = "/rest/template/download",method = RequestMethod.GET)
    DownloadResponse download();

    @RequestMapping(value = "/rest/template/upload/{course}",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    DownloadResponse upload(@PathVariable("course") String course, @RequestPart(value = "file")MultipartFile file);


}
