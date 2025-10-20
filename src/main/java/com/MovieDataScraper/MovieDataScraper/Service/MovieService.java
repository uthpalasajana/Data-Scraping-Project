package com.MovieDataScraper.MovieDataScraper.Service;


import com.MovieDataScraper.MovieDataScraper.Dto.MovieDto;
import com.MovieDataScraper.MovieDataScraper.Dto.TvShowDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final WebDriver driver;
    private final List<MovieDto> movies = new ArrayList<>();
    private final List<TvShowDto> tvShows = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    public MovieService() {

        //headless approach without loading chrome
        //this also tells how to run chrome under selenium
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");//disable sandbox chrome security
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

    }

    //this method use to give the waiting time for page to scrapping using selenium
    private void sleep(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    //read movies from json files
    private List<String> readMovieNames(String filePath) {
        List<String> names = new ArrayList<>();

        //use file handling to read movies the json file
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonArray()) {
                JsonArray array = jsonElement.getAsJsonArray();
                for (JsonElement element : array) {
                    names.add(element.getAsString());
                }
            }
        } catch (Exception e) {
           logger.error(e.getMessage());
        }

        return names;
    }

    //this method calls necessary method in this class in order and it has been called inside the main class
    @Scheduled(fixedRate = 30 * 60 *1000)  //schedual to run this method for every 30 minutes
    public void runScraper() {

        List<String> movieNames = readMovieNames("src/main/resources/movieInput.json");//file path where the json file that contains the movie names

        for (String movie : movieNames) {
            System.out.println(movie);
            search(movie);
            getAllMovies();
            saveAsJson("output/" + movie + ".json","output/allTvShows.json");
            clearData();
        }

    }

    //this method scrape the website using the provided movie name as a parameter
    public void search(String movieName) {
        driver.get("https://todaytvseries.one/tvshows/" + movieName);//this is the website url that we need to scrape
        sleep(7); //give the waiting time for the searching


        //in here to get the data from the website used to xpath of the related elements
        try {
            WebElement container = driver.findElement(By.xpath("//*[@id='single']/div[1]/div[1]"));

            String title = container.findElement(By.xpath(".//div[2]/h1")).getText();
            String poster = container.findElement(By.xpath(".//div[1]/img")).getAttribute("src");
            String scheduledTime = container.findElement(By.xpath(".//div[2]/div[1]/span[1]")).getText();
            String type = container.findElement(By.xpath(".//div[2]/div[3]/a[1]")).getText();
            String description = driver.findElement(By.xpath("//*[@id='info']/div")).getText();

            MovieDto movie = new MovieDto(title, poster, type, scheduledTime, description);
            movies.add(movie);

            System.out.println(movie);

        } catch (Exception e) {
            logger.error( e.getMessage());
        }
    }

    //this method is to get all the tv shows
    public void getAllMovies() {
        try {
            driver.get("https://todaytvseries.one/tvshows/");
            sleep(10);
            List<WebElement> movieElements = driver.findElements(By.xpath("//*[@id='archive-content']/article"));
            logger.info(" Total movies : " + movieElements.size());


            for (WebElement movieElement : movieElements) {
                String movieName = movieElement.findElement(By.xpath(".//h3/a")).getText();
                String movieUrl = movieElement.findElement(By.xpath(".//h3/a")).getAttribute("href");

                TvShowDto tvShow = new TvShowDto(movieName, movieUrl);
                tvShows.add(tvShow);
                System.out.println(tvShow);
            }

        } catch (Exception e) {
          logger.error( e.getMessage());
        }
    }

    //this method used to save the data in json format
    public void saveAsJson(String fileName,String fileName2) {
        JsonArray array = new JsonArray();
        JsonArray array2 = new JsonArray();


        for (MovieDto movie : movies) {
            JsonObject object = new JsonObject();
            object.addProperty("title", movie.getTitle());
            object.addProperty("poster", movie.getPoster());
            object.addProperty("type", movie.getType());
            object.addProperty("time", movie.getTime());
            object.addProperty("description", movie.getDescription());
            array.add(object);
        }

        for (TvShowDto tvShow : tvShows) {
            JsonObject object = new JsonObject();
            object.addProperty("name", tvShow.getTitle());
            object.addProperty("url", tvShow.getUrl());
            array2.add(object);
        }

        //used file handling here to write the data to a json file
        try {
            FileWriter writer = new FileWriter(fileName);
            FileWriter writer2 = new FileWriter(fileName2);
            writer.write(array.toString());
            writer2.write(array2.toString());
            writer.flush();
            writer2.flush();
            writer.close();
            writer2.close();
            logger.info("JSON saved: " + fileName);
            logger.info("JSON saved: " + fileName2);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    //clear the data in the list
    public void clearData() {
        movies.clear();
    }
}
