# 🎬 Movie Data Scraper
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![JSON](https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white)
![Headless Chrome](https://img.shields.io/badge/Headless%20Chrome-4285F4?style=for-the-badge&logo=googlechrome&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

A fully automated web scraping service that collects movie and TV show data from [todaytvseries.one](https://todaytvseries.one), including titles, posters, genres, and descriptions.  
Built with **Spring Boot**, **Selenium** this project display how to scrap data from a simple website.

---

## 🚀 Features

🎥 **Movie Scraping** – Extracts details such as title, poster, type, schedule, and description.  
📺 **TV Show Listing** – Fetches all available TV shows with names and URLs.  
🕒 **Scheduled Automation** – Runs automatically every 30 minutes using Spring Scheduler.  
💾 **JSON Output** – Saves data as structured JSON files for easy processing.  
⚡ **Headless Browser Mode** – Runs Chrome in the background for faster, lightweight scraping.  


---

## 🛠️ Tech Stack

| Layer | Technology |
|--------|-------------|
| Backend | Spring Boot (Java) |
| Web Automation | Selenium WebDriver (Headless Chrome) |
| Data Handling | Gson (Google JSON Library) |
| Scheduling | Spring Scheduler |
| Output Format | JSON Files |

---

## ⚙️ Setup and Run

### 1️⃣ Prerequisites
- Java 17 or higher  
- Google Chrome installed
- IDE Like IntelliJ idea

### 2️⃣ Setup Steps
1. **Clone the project**
   
   git clone https://github.com/uthpalasajana/Data-Scraping-Project.git
   Add input movie list :
   Create a file: src/main/resources/movieInput.json
        
   json
   <img width="299" height="131" alt="image" src="https://github.com/user-attachments/assets/a6669d9f-dd34-4807-b507-6a06bcb550d5" />

   Build and run the application
   Output JSON files are saved in the output/ folder
   Individual movie files → output/{movieName}.json
   All TV shows list → output/allTvShows.json

💡 How It Works

Reads movie names from movieInput.json.
Scrapes data using Selenium (headless Chrome).
Extracts elements using predefined XPath selectors.
Saves results in JSON format.
Save Each Movie in Seperate Json File
Automatically repeats every 30 minutes.

🧩 Assumptions & Concerns

Website structure of todaytvseries.one remains stable.
Each movie name corresponds to a valid URL ("https://todaytvseries.one/tvshows/" + movieName).
If any data or page is missing, it is logged instead of causing a crash.

🧾 Output Example

Sample Movie JSON:


<img width="734" height="169" alt="image" src="https://github.com/user-attachments/assets/fb4f4145-4d7f-4cc6-b633-495c38cff502" />

All TV Shows JSON:


<img width="1061" height="765" alt="image" src="https://github.com/user-attachments/assets/a985732c-cfe9-4455-a015-a0ac875da01a" />


