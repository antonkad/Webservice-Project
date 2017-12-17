package com.projetWebservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projetWebservice.Models.Film;

@SpringBootApplication
public class ProjetWebServiceApplication {

    public static List<Film> filmList;

	public static void main(String[] args) {
		SpringApplication.run(ProjetWebServiceApplication.class, args);		
	}
}
