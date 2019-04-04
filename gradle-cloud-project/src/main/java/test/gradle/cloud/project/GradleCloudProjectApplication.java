package test.gradle.cloud.project;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@RefreshScope
@EnableMongoRepositories(basePackages = "test.gradle.cloud.project.repository")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class GradleCloudProjectApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GradleCloudProjectApplication.class);
	}

	public static void main(String[] args) {
		new GradleCloudProjectApplication().configure(new SpringApplicationBuilder(GradleCloudProjectApplication.class)).run(args);
	}
}
