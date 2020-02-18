package com.ms.redis.service.impl;

import com.ms.redis.common.ResponsePayload;
import com.ms.redis.dto.StudentSaveRequestDto;
import com.ms.redis.dto.StudentSearchResponseDto;
import com.ms.redis.entity.Student;
import com.ms.redis.repository.StudentRepository;
import com.ms.redis.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Eshan
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public ResponsePayload storeStudent(StringBuilder chainLog, StudentSaveRequestDto studentSaveRequestDto) {

        Student student = new Student();
        student.setId(studentSaveRequestDto.getId());
        student.setName(studentSaveRequestDto.getName());
        student.setGrade(studentSaveRequestDto.getGrade());
        studentRepository.save(student);

        return new ResponsePayload.Builder(HttpStatus.CREATED, chainLog)
                .addPayload("Student has been successfully saved")
                .build();
    }

    @Override
    public ResponsePayload getAllStudents() {

        final List<Student> studentList = (List<Student>) studentRepository.findAll();
        if (studentList.isEmpty()){
            return new ResponsePayload.Builder(HttpStatus.NOT_FOUND, new StringBuilder())
                    .addPayload("Record not found")
                    .build();
        } else {
            final List<StudentSearchResponseDto> userTypeDtoList = studentList.stream().map(entity-> {
                StudentSearchResponseDto studentSearchResponseDto = new StudentSearchResponseDto();
                studentSearchResponseDto.setId(entity.getId());
                studentSearchResponseDto.setName(entity.getName());
                studentSearchResponseDto.setGrade(entity.getGrade());
                return studentSearchResponseDto;
            }).collect(Collectors.toList());
            return new ResponsePayload.Builder(HttpStatus.OK, new StringBuilder())
                    .addPayload("Student has been successfully return")
                    .addPayload(userTypeDtoList)
                    .build();
        }
    }
}
