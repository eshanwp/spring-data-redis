package com.ms.redis.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author Eshan
 */
@Data
@ToString
public class StudentSaveRequestDto {
    private String id;
    private String name;
    private int grade;
}
