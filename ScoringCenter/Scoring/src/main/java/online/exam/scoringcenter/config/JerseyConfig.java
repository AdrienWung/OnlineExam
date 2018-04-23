package online.exam.scoringcenter.config;

import io.swagger.jaxrs.config.BeanConfig;
import online.exam.scoringcenter.jerseyservice.ScoringEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@Primary
@Qualifier
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    @Value("${spring.jersey.application-path:/}")
    private String apiPath;

    public JerseyConfig() {
        this.registerEndpoints();
    }

    @PostConstruct
    public void init() {
        this.configureSwagger();
    }

    private void registerEndpoints() {
        this.register(ScoringEndpoint.class);
    }

    private void configureSwagger() {
        registerEndpoints();

        BeanConfig config = new BeanConfig();
        config.setTitle("Scoring Center API");
        config.setVersion("1.0.0");
        config.setContact("Carter Wang");
        config.setSchemes(new String[]{"http", "https"});
        config.setBasePath(this.apiPath);
        config.setResourcePackage("online.exam.scoringcenter.jerseyservice");
        config.setPrettyPrint(true);
        config.setScan(true);
    }

}
