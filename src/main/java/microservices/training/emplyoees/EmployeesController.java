package microservices.training.emplyoees;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        return employeesService.listEmployees(prefix);
    }


    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable("id") Long id) {
        return employeesService.findEmployeeById(id);
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity findEmployeeById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(employeesService.findEmployeeById(id));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }
    */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") Long id, @RequestBody UpdateEmployeeCommand command) {
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) {
        employeesService.deleteEmployee(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem>handleNotFound(IllegalArgumentException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("employees/not-found"))
                .withTitle("Not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
