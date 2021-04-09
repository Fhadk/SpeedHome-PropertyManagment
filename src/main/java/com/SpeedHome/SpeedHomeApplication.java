package com.SpeedHome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.SpeedHome.model.ActiveUserData;

@SpringBootApplication
public class SpeedHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeedHomeApplication.class, args);
	}
		
	@Bean
	public ActiveUserData activeUserStore(){
	    return new ActiveUserData();
	}

}
