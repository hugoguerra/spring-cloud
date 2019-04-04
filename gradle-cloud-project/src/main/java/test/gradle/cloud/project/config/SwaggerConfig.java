package test.gradle.cloud.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig  {
    private static final String SCAN_PACKAGES = "test.gradle.cloud.project";

    @Bean
    public Docket api() {
        System.out.println("SCAN_PACKAGES : " + SCAN_PACKAGES);
        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(SCAN_PACKAGES))
                    .build()
                    .apiInfo(new ApiInfoBuilder().title("Service Swagger Documentation")
                                    .description("API Resources Documentation")
                                        .build());
    }
}
