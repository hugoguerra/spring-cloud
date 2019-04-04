package test.gradle.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigServer
public class GradleCloudServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradleCloudServerApplication.class, args);
	}

}
