package com.ms.redis.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author Eshan
 */
@RedisHash("Student")
@Data
@ToString
public class Student implements Serializable {

    private String id;
    private String name;
    private int grade;
}
