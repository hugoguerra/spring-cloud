import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import test.gradle.cloud.project.BaseTest

class ClientTest extends BaseTest {

    def "List of all clients"() {
        given : "Definition of rest URI"
            def URI = "/gradle/client"
        when: "Make the call to the URI ${URI}"
            def mvcResult =  mockMvc.perform(MockMvcRequestBuilders.get(URI)).andReturn()
        then: "Validate the response"
            mvcResult.response.status == HttpStatus.OK.value()
            mvcResult.response.getContentAsString() == []
    }
}