package com.ms.redis.controller;

import com.ms.redis.common.Constant;
import com.ms.redis.common.ResponsePayload;
import com.ms.redis.dto.StudentSaveRequestDto;
import com.ms.redis.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eshan
 */
@RestController
@RequestMapping(Constant.API_ROOT)
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * store student
     *
     * @author Eshan
     */
    @PostMapping(value = Constant.STORE_STUDENT, produces = Constant.PRODUCE_TYPE_JSON, consumes = Constant.CONSUMES_TYPE_JSON)
    public ResponseEntity<ResponsePayload> storeStudent(
            @RequestBody StudentSaveRequestDto studentSaveRequestDto
    ) {
        StringBuilder chainLog = new StringBuilder()
                .append("|ReqBdy:" + studentSaveRequestDto.toString());
        ResponsePayload responsePayload = studentService.storeStudent(chainLog, studentSaveRequestDto);
        return ResponseEntity.status(responsePayload.getHttpCode()).body(responsePayload);
    }

    /**
     * get all student for selection box
     *
     * @author Eshan
     */
    @GetMapping(value = Constant.GET_ALL_STUDENTS, produces = Constant.PRODUCE_TYPE_JSON)
    public ResponseEntity<ResponsePayload> getAllStudents() {
        ResponsePayload responsePayload = studentService.getAllStudents();
        return ResponseEntity.status(responsePayload.getHttpCode()).body(responsePayload);
    }

}
