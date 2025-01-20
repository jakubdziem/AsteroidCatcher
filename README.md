##   AsteroidCatcher 
Asteroid Catcher is an API that provides daily information on asteroids passing near Earth and helps users identify which asteroids are visible in their location based on meteorological data and geographical coordinates. By integrating data from multiple APIs, it allows users to observe, learn about, and prepare for asteroid observations with ease. 🔭☄️
## Features
<ul>
<li>Retrieve a list of asteroids observable today</li>
<li>Fetch asteroids observable on a specific date</li>
<li>Get asteroids visible from a specified city</li>
<li>Fetch asteroids visible from specified geographic coordinates</li>
<li>Get meterological information in provided localization</li>
<li>Learn whether weather conditions are suitable for observing asteroids and receive advice on which observation equipment to use.</li>
</ul>

## Technologies
<ul>
<li>Spring Boot - setting up a web server, handling HTTP requests, and simplifying development</li>
<li>Spring Data - managing data access and reducing boilerplate code</li>
<li>Thymeleaf - rendering dynamic HTML pages</li>
<li>Bootstrap - ensuring the frontend is well-styled and responsive</li>
<li>Lombok - reducing boilerplate code with annotations</li>
<li>Maven - managing the project's build lifecycle and dependencies</li>
<li>HTML5 - structuring web documents.</li>
</ul>

## Usage
### Website:
```bash
https://asteroidcatcher.onrender.com/asteroidcatcher
```
### Operations on API:
<ul>
<li>Get Asteroids for Today:
Example request: 
  
```bash
GET https://asteroidcatcher.onrender.com/asteroids
```

</li>
<li>Get Asteroids for a Specific Date:
Example request: 
  
```bash
GET https://asteroidcatcher.onrender.com/asteroids/2024-07-25
```

</li>
<li>Get Asteroids Visible from a Specific City:
Example request: 
  
```bash
GET https://asteroidcatcher.onrender.com/visibility/Jakarta
```

</li>
<li>
Get Asteroids Visible from Specific Coordinates:
Example request: 
  
```bash
GET https://asteroidcatcher.onrender.com/visibility/41.71/67.31
```

</li>
<li>Get Example Visibility of Asteroids (Hardcoded Coordinates):
Example request:
  
```bash
GET https://asteroidcatcher.onrender.com/visibility
```

</li>
</ul>

## Screenshots
### Home Page Overview
![Zrzut ekranu 2025-01-20 143104](https://github.com/user-attachments/assets/20ba527c-4139-42c0-adaf-de5bc6608963)
![Zrzut ekranu 2025-01-20 143116](https://github.com/user-attachments/assets/27093725-e2b5-40ea-8925-a0a7921d6334)
![Zrzut ekranu 2025-01-20 143125](https://github.com/user-attachments/assets/2f0e3631-d4ab-4aab-bd34-381abc4178b7)
The home page of Asteroid Catcher (/asteroidcatcher) provides users with an introduction to the platform and highlights its main features.
### Asteroids List for Today
![Zrzut ekranu 2025-01-20 143142](https://github.com/user-attachments/assets/d1ea5106-739f-4ddc-9d5b-4a6ba6d0133f)
A dynamic list of asteroids observable today, retrieved through the API at /asteroidslist.
### Visible Asteroids in New York
![Zrzut ekranu 2025-01-20 143208](https://github.com/user-attachments/assets/d9a7b416-e1fb-4197-a530-8fedc90cf472)
Asteroids currently visible in New York City, displayed with supporting meteorological data for observation suitability. Endpoint: /visibleasteroids.
### Asteroids Visible at Specific Coordinates
![Zrzut ekranu 2025-01-20 143322](https://github.com/user-attachments/assets/fde064c2-eddb-4bec-9f94-be799c2d6df4)
Endpoint: /visibleasteroids.



