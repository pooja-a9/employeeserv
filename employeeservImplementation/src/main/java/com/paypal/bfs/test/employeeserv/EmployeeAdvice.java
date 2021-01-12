package com.paypal.bfs.test.employeeserv;

import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.exception.EmployeeValidationFailedException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@SpringBootApplication
public class EmployeeAdvice {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeAdvice.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}


@ControllerAdvice
class ApplicationAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmployeeValidationFailedException.class)
    ResponseEntity<List<String>> EmployeeValidationFailedHandler(EmployeeValidationFailedException ex) {
        return new ResponseEntity<>(ex.getErrors(),HttpStatus.BAD_REQUEST);
    }
}
