package microservices.training.emplyoees;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

	/*@Bean
	public HelloService employeeService() {
		return new HelloService();
	}
	*/

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
