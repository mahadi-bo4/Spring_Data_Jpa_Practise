package com.example.jpatest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.email= ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("select s from Student s where s.firstName= ?1 and s.age>= ?2")
    List <Student> selectStudentWhereFirstNameAndAgeGreaterOrEquals(String firstName, Integer age);


    @Query(value = "select * from student where first_Name= :firstName and age>= :age",
            nativeQuery = true)
    List <Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualsNative(@Param("firstName") String firstName,
                                                                          @Param("age") Integer age);


    @Transactional
    @Modifying
    @Query("delete from Student s where s.id= ?1")
    int deleteStudentByid(Long id);

}
