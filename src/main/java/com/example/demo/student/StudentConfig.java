package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;


@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository){

       return args -> {
Student mikeCvet = new Student("Mike Cvet",
        "mikecvet@yahoo.com",
        LocalDate.of(1997,05,06)
        );
Student tommyCvet = new Student("Tommy Cvet",
        "tommycvet@gmail.com",
        LocalDate.of(1997,05,06));
Student newman = new Student("Michel Newman","michelnewman.com",LocalDate.of(1996,03,11));
           studentRepository.saveAll(List.of(mikeCvet, tommyCvet, newman));
       };


    }

}
