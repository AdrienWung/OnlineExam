package online.exam.datacenter.config;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import online.exam.datacenter.jerseyservice.OnlineExamEndpoint;
import online.exam.datacenter.service.QuestionService;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
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
		this.register(JacksonFeature.class);
		this.register(MultiPartFeature.class);
		this.register(OnlineExamEndpoint.class);
	}

	private void configureSwagger() {
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);

		BeanConfig config = new BeanConfig();
		config.setTitle("Exam Data Center API");
		config.setVersion("1.0.0");
		config.setContact("Carter Wang");
		config.setSchemes(new String[] { "http", "https" });
		config.setBasePath(this.apiPath);
		config.setResourcePackage("online.exam.datacenter.jerseyservice");
		config.setPrettyPrint(true);
		config.setScan(true);
	}

}
