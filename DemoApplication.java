package com.example.demo;

import com.example.demo.Dao.AppUserDao;
import com.example.demo.Dao.RoleDao;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new RoleDao(null, "ROLE_USER"));
			userService.saveRole(new RoleDao(null, "ROLE_MANAGER"));
			userService.saveRole(new RoleDao(null, "ROLE_ADMIN"));
			userService.saveRole(new RoleDao(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new AppUserDao(null, "Sarthak Shashi", "Sarthak2501", "1234", new ArrayList<>()));
			userService.saveUser(new AppUserDao(null, "Shubham Shashi", "Shubham0408", "2341", new ArrayList<>()));
			userService.saveUser(new AppUserDao(null, "ranjit singh", "Ranjit3261", "3421", new ArrayList<>()));
			userService.saveUser(new AppUserDao(null, "Himanshu vedi", "Himanshu4848", "4321", new ArrayList<>()));

			userService.addRoleToUser("Sarthak2501", "ROLE_USER");
			userService.addRoleToUser("Shubham0408", "ROLE_MANAGER");
			userService.addRoleToUser("Ranjit3261", "ROLE_ADMIN");
			userService.addRoleToUser("Himanshu4848", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("Himanshu4848", "ROLE_ADMIN");
			userService.addRoleToUser("Himanshu4848", "ROLE_USER");
		};
	}
}

