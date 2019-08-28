package org.example.ws.web.api;

import org.example.ws.dto.RegisterStudentRequest;
import org.example.ws.dto.RegisterStudentResponse;
import org.example.ws.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(
            value = "/api/registerStudent",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterStudentResponse> registerStudent(@RequestBody RegisterStudentRequest registerStudentRequest) {

        RegisterStudentResponse response = studentService.registerStudent(registerStudentRequest);

        return new ResponseEntity<RegisterStudentResponse>(response,
                HttpStatus.OK);
    }

}
