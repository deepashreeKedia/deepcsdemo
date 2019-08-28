package org.example.ws.service;

import org.example.ws.dto.RegisterBookResponse;
import org.example.ws.dto.RegisterStudentRequest;
import org.example.ws.dto.RegisterStudentResponse;

public interface StudentService {

    RegisterStudentResponse registerStudent(RegisterStudentRequest registerStudentRequest);
}
