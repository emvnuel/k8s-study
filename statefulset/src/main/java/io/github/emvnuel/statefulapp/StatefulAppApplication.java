package io.github.emvnuel.statefulapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
@Slf4j
public class StatefulAppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StatefulAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Creating directory");
        File f1 = new File("/var/images");
        boolean bool = f1.mkdir();
        if(bool){
            log.info("Folder is created successfully");
        }else{
            log.warn("Folder already exists or error");
        }
    }
}
