package com.example.jpatest;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class JpaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaTestApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepo) {

        return args -> {

            getFakeStudent(studentRepo);

            Sort sort = Sort.by("firstName").ascending()
                    .and(Sort.by("age").descending());


            studentRepo.findAll(sort)
                    .forEach(student -> System.out.println(
                            student.getFirstName() + " " + student.getAge())
                    );

        };
    }



    private void getFakeStudent(StudentRepo studentRepo){
        Faker faker = new Faker();
        for(int i = 0; i<25; i++){
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s_%s@mahadiMail.com",firstName,lastName);

            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(20, 55)
            );

            studentRepo.save(student);
        }
    };
}
