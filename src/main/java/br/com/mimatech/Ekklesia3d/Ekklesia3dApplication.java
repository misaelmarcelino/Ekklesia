package br.com.mimatech.Ekklesia3d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ekklesia3dApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ekklesia3dApplication.class, args);
	}

}
