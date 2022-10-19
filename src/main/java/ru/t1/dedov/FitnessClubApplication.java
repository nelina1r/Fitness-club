package ru.t1.dedov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class FitnessClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitnessClubApplication.class, args);
    }

}
