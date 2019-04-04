package test.gradle.cloud.project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GradleCloudProjectApplicationTests {

    @Value("${message}")
    private String messageTest;

    @Before
	public void setUp() {

    }

    @Test
    public void test() {
        System.out.println(this.messageTest);
    }


}
