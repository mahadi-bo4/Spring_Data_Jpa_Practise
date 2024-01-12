package com.example.jpatest;

import org.springframework.data.repository.CrudRepository;

public interface StudentIdCardRepo
        extends CrudRepository<StudentIdCard, Long> {
}
