package com.example.k8stest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
@RestController
@Slf4j
public class K8sTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(K8sTestApplication.class, args);
    }

    boolean alive = true;
    private int counter = 0;
    final String hostname = System.getenv().getOrDefault("HOSTNAME", "unknown");

    @GetMapping
    public String hello() {
        counter++;
        return "Hello "+counter+" from "+hostname+"\n";
    }

    @RequestMapping("/sysresources")
    public String getSystemResources() {
        long memory = Runtime.getRuntime().maxMemory();
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("/sysresources " + hostname);
        return
                " Memory: " + (memory / 1024 / 1024) +
                        " Cores: " + cores + "\n";
    }

    @RequestMapping("/kill")
    public void kill() {
        alive = false;
    }

    @RequestMapping("/consume")
    public String consumeSome() {
        System.out.println("/consume " + hostname);

        Runtime rt = Runtime.getRuntime();
        StringBuilder sb = new StringBuilder();
        long maxMemory = rt.maxMemory();
        long usedMemory = 0;
        // while usedMemory is less than 80% of Max
        while (((float) usedMemory / maxMemory) < 0.80) {
            sb.append(System.nanoTime() + sb.toString());
            usedMemory = rt.totalMemory();
        }
        String msg = "Allocated about 80% (" + humanReadableByteCount(usedMemory, false) + ") of the max allowed JVM memory size ("
                + humanReadableByteCount(maxMemory, false) + ")";
        System.out.println(msg);
        return msg + "\n";
    }

    @GetMapping("/health")
    public ResponseEntity health() {
        return ResponseEntity.ok("Tinindo trincando\n");
    }

    @GetMapping("/alive")
    public ResponseEntity alive() {
        if (alive) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit)
            return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

}
