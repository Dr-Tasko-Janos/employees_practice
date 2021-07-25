package microservices.training.emplyoees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class HelloServiceTest {

    @Test
    void sayHello() {
        HelloService helloService = new HelloService();
        String message = helloService.sayHello();

        assertThat(message).startsWith("Hello");
    }
}
