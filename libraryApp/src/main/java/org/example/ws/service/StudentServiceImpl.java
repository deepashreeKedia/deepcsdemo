package org.example.ws.service;

import org.example.ws.constant.AppConstant;
import org.example.ws.dto.RegisterStudentRequest;
import org.example.ws.dto.RegisterStudentResponse;
import org.example.ws.model.Student;
import org.example.ws.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public RegisterStudentResponse registerStudent(RegisterStudentRequest registerStudentRequest) {

        // TODO add a check if the title already exists

        String studentId = UUID.randomUUID().toString();


        Student newStudent = new Student();
        newStudent.setId(studentId);
        newStudent.setName(registerStudentRequest.getName());
        newStudent.setStatus(AppConstant.ACTIVE);

        Student student = studentRepository.save(newStudent);

        //prepare response
        RegisterStudentResponse response = new RegisterStudentResponse();
        response.setName(student.getName());
        response.setStudentId(student.getId());


        return response;

    }
}
