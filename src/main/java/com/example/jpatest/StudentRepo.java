package com.example.jpatest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {


                             //JPQL Test
    @Query("select s from Student s where s.email= ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("select s from Student s where s.firstName= ?1 and s.age>= ?2")
    List <Student> selectStudentWhereFirstNameAndAgeGreaterOrEquals(String firstName, Integer age);


                              //Native Query Test
    @Query(value = "select * from student where first_Name= ?1 and age>= ?2",
            nativeQuery = true)
    List <Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualsNative(String firstName,
                                                                          Integer age);

}
