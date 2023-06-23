package com.example.pattern;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PatternApplication {
    public static String LOG_PATH;
    public static void main(String[] args) {
        SpringApplication.run(PatternApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        String p = System.getProperty("catalina.home");
        LOG_PATH = p + "/logs";
        // CHECK LOG PATH ==> Check path exist
    }
}
