package test.gradle.cloud.project

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.apache.catalina.core.ApplicationContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Shared
import spock.lang.Specification
import test.gradle.cloud.project.config.mongo.EmbeddedMongoRule
import test.gradle.cloud.project.resource.ClientResource

@SpringBootTest(classes = [GradleCloudProjectApplication.class, EmbeddedMongoRule.class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseTest extends Specification {

    @Value('${spring.data.mongodb.host}')
    private String host;

    @Value('${spring.data.mongodb.port}')
    private String port;

    @Value('${spring.data.mongodb.database}')
    private String database;

    public EmbeddedMongoRule embeddedMongoRule

    @Shared
    ApplicationContext context

    public static WireMockServer wireMockServer

    public MockMvc mockMvc;

    def setup() {
        // This is the setup of the wiremock used to validate external dependencies.
        this.wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8181).httpsPort(8443))

        // this is the setup for the rest controller ClientResource
        this.mockMvc = MockMvcBuilders.standaloneSetup(ClientResource.class).build()

//        this.embeddedMongoRule = new EmbeddedMongoRule(this.host, Integer.valueOf(this.port), this.database)
//        this.embeddedMongoRule.start()
    }

    def cleanup() {
        //Stop the wireMockServer
        this.wireMockServer.stop()

        //Drop datadase
//        this.embeddedMongoRule.after()

        //Stop mongod
//        this.embeddedMongoRule.stopMongo()
    }
}
