package com.gato.demo;

import com.gato.demo.model.Server;
import com.gato.demo.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GatoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatoSpringBootApplication.class, args);
	}


}
