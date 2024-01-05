package com.example.jpatest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaTestApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepo) {
        return args -> {
            Student ninNi = new Student(
                    "Nin2",
                    "Ni",
                    "ninni@email.com",
                    18);

            Student ninNi2 = new Student(
                    "Nin2",
                    "Ni",
                    "ninni2@email.com",
                    18);

            Student rahim = new Student(
                    "Rahim",
                    "Hossain",
                    "rahimho@email.com",
                    30);

            System.out.println("Adding Students");
            studentRepo.saveAll(List.of(ninNi, ninNi2, rahim));

//            System.out.print("Total Student = ");
//            System.out.println(studentRepo.count());
//
//            studentRepo
//                    .findById(2L)
//                    .ifPresentOrElse(
//                            System.out::println,
//                            () -> {
//                                System.out.println("Student not found");
//                            });
//
//            studentRepo
//                    .findById(4L)
//                    .ifPresentOrElse(
//                            System.out::println,
//                            () -> {
//                                System.out.println("Student not found");
//                            });
//
//
//            System.out.println("Select all students");
//            List <Student> students = studentRepo.findAll();
//            students.forEach(System.out::println);
//
//            System.out.println("Delete Student");
//            studentRepo.deleteById(1L);
//
//            System.out.print("Final students number = ");
//            System.out.println(studentRepo.count());
            studentRepo
                    .findStudentByEmail("rahimho@email.com")
                    .ifPresentOrElse(
                            System.out::println,
                            () -> {System.out.println("Student not found");});



            studentRepo
                    .selectStudentWhereFirstNameAndAgeGreaterOrEquals(
                    "Nin2",
                            18
                    ).forEach(System.out::println);

            studentRepo
                    .selectStudentWhereFirstNameAndAgeGreaterOrEqualsNative(
                            "Nin2",
                            18
                    ).forEach(System.out::println);
        };
    }
}
