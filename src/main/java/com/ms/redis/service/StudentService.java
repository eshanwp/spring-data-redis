package com.ms.redis.service;

import com.ms.redis.common.ResponsePayload;
import com.ms.redis.dto.StudentSaveRequestDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eshan
 */
@Transactional(rollbackFor = Exception.class)
public interface StudentService {
    ResponsePayload storeStudent(StringBuilder chainLog, StudentSaveRequestDto studentSaveRequestDto);

    ResponsePayload getAllStudents();

}
