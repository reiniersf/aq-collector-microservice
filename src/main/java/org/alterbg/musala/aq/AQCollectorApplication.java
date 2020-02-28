package org.alterbg.musala.aq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AQCollectorApplication {

  public static void main(String[] args) {
    SpringApplication.run(AQCollectorApplication.class, args);
  }
}
