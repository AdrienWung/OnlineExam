package online.exam.datacenter.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by SK on 2017/11/30.
 */
public interface TemplateService {

    void importFile(InputStream file, String course) throws IOException;
}
