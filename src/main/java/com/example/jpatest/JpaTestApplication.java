package com.example.jpatest;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class JpaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaTestApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepo) {

        return args -> {
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s_%s@mahadiMail.com",firstName,lastName);

            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(20, 55)
            );

            student.addBook(
                    new Book("Himu", LocalDateTime.now().minusYears(1L)));

            student.addBook(
                    new Book("Himu Asbe", LocalDateTime.now().minusWeeks(4L)));

            student.addBook(
                    new Book("Himu Ese Chole Gese", LocalDateTime.now().minusDays(4)));

            StudentIdCard studentIdCard = new StudentIdCard("125454511", student);
            student.setStudentIdCard(studentIdCard);
            studentRepo.save(student);

            studentRepo.findById(1L).ifPresent(s ->{
                System.out.println("fetch book lazy...");
                List<Book> books = student.getBooks();
                books.forEach(book -> {
                    System.out.println(s.getFirstName() + " Borrowed " + book.getBookName());
                });
            });
//            studentIdCardRepo.findById(1L).ifPresent(System.out::println);

//            studentIdCardRepo.deleteById(1L);
//            studentRepo.deleteById(1L);
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
