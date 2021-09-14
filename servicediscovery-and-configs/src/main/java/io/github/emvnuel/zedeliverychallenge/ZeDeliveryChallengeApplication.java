package io.github.emvnuel.zedeliverychallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ZeDeliveryChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeDeliveryChallengeApplication.class, args);
    }

}
