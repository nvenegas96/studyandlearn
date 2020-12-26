package com.tesis.studyandlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class StudyandlearnApplication {


    public static void main(String[] args) {
        SpringApplication.run(StudyandlearnApplication.class, args);

       /* BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "123abc";
        String encodedPassword = passwordEncoder.encode(password);

        System.out.println();
        System.out.println("Password is         : " + password);
        System.out.println("Encoded Password is : " + encodedPassword);
        System.out.println("Invalid Password is : " + encodedPassword + "junk");
        System.out.println();


        boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword+"junk");
        System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);*/
    }


}
