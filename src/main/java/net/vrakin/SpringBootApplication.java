package net.vrakin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class   SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}
}
