package com.startup.myhome;

import com.startup.myhome.dto.request.RegisterRequest;
import com.startup.myhome.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

import static com.startup.myhome.enumeration.Role.ADMIN;

@SpringBootApplication
@RequiredArgsConstructor
public class MyHomeApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyHomeApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationServiceImpl service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.name("Admin")
					.surname("Admin")
					.email("admin@mail.com")
					.password("password")
					.roles(Collections.singleton(ADMIN))
					.build();
			System.out.println("Admin token: " + service.register(admin).getToken());
		};
	}

}
