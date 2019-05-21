package main.java.com.pitang.ApiRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import main.java.com.pitang.ApiRest.repository.ExtendedRepositoryImpl;

@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);

	}

	@Configuration
	@EnableJpaRepositories(basePackages = "main.java.com.pitang.ApiRest.repository", repositoryBaseClass = ExtendedRepositoryImpl.class)
	public class UsuarioJPAH2Config {
	}

}
