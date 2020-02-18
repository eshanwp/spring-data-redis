package com.ms.redis.repository;

import com.ms.redis.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Eshan
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
