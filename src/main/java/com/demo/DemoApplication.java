package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.services.SubmissionService;
import com.demo.services.TaskService;
import com.demo.services.TutorialService;
import com.demo.services.UserService;


@SpringBootApplication
public class DemoApplication {

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public SubmissionService submissionService() {
		return new SubmissionService();
	}

	@Bean
	public TaskService taskService() {
		return new TaskService();
	}

	@Bean
	public TutorialService tutorialService() {
		return new TutorialService();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


}
