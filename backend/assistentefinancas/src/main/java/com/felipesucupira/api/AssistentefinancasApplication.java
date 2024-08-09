package com.felipesucupira.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.felipesucupira.controller.UsuarioController;

@SpringBootApplication
@ComponentScan(basePackageClasses = UsuarioController.class)
public class AssistentefinancasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssistentefinancasApplication.class, args);
	}

}
