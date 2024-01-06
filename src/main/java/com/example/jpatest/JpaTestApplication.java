package com.example.jpatest;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class JpaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaTestApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepo) {

        return args -> {

            getFakeStudent(studentRepo);
//            sorting(studentRepo);

            PageRequest pageRequest = PageRequest.of(
                    0,
                    5,
                    Sort.by("firstName").ascending()
            );

            Page <Student> page = studentRepo.findAll(pageRequest);
            System.out.println(page);

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

    private void sorting(StudentRepo studentRepo){
        Sort sort = Sort.by("firstName").ascending()
                .and(Sort.by("age").descending());


        studentRepo.findAll(sort)
                .forEach(student -> System.out.println(
                        student.getFirstName() + " " + student.getAge())
                );

    }
}
