package com.MovieDataScraper.MovieDataScraper;

import com.MovieDataScraper.MovieDataScraper.Service.MovieService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling//for scheduled task
public class MovieDataScraperApplication {

	@Autowired
	private MovieService movieService;

	public static void main(String[] args) {
		SpringApplication.run(MovieDataScraperApplication.class, args);



	}

}
