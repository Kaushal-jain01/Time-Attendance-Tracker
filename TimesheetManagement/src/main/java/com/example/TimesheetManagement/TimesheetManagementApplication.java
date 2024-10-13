package com.example.TimesheetManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class })
public class TimesheetManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetManagementApplication.class, args);
		System.out.println("Hello Spring Boot");
	}

}
