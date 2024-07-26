##   AsteroidCatcher 
Asteroid Catcher is an API that provides daily information on asteroids passing near Earth and helps users identify which asteroids are visible in their location based on meteorological data and geographical coordinates. By integrating data from multiple APIs, it allows users to observe, learn about, and prepare for asteroid observations with ease. 🔭☄️
## Features
<ul>
<li>Retrieve a list of asteroids observable today</li>
<li>Fetch asteroids observable on a specific date</li>
<li>Get asteroids visible from a specified city</li>
<li>Fetch asteroids visible from specified geographic coordinates</li>
<li>Get meterological information in provided localization</li>
<li>Learn about if weather conditions are suited to observe asteroids and advice about which device to use for it</li>
</ul>

## Technologies
<ul>
<li>Spring Boot - setting up a web server, handling HTTP requests, and simplifying development</li>
<li>Spring Data - managing data access and reducing boilerplate code</li>
<li>Thymeleaf - rendering dynamic HTML pages</li>
<li>Bootstrap - ensuring the frontend is well-styled and responsive</li>
<li>Lombok - reducing boilerplate code with annotations</li>
<li>Maven - managing the project's build lifecycle and dependencies</li>
<li>HTML5 - structuring the documents</li>
</ul>

## Usage
### Website:
```bash
https://asteroidcatcher-db7fb1c99a25.herokuapp.com/asteroidcatcher
```
### Operations on API:
<ul>
<li>Get Asteroids for Today:
Example request: 
  
```bash
GET https://asteroidcatcher-db7fb1c99a25.herokuapp.com/asteroids
```

</li>
<li>Get Asteroids for a Specific Date:
Example request: 
  
```bash
GET https://asteroidcatcher-db7fb1c99a25.herokuapp.com/asteroids/2024-07-25
```

</li>
<li>Get Asteroids Visible from a Specific City:
Example request: 
  
```bash
GET https://asteroidcatcher-db7fb1c99a25.herokuapp.com/visibility/Jakarta
```

</li>
<li>
Get Asteroids Visible from Specific Coordinates:
Example request: 
  
```bash
GET https://asteroidcatcher-db7fb1c99a25.herokuapp.com/visibility/41.71/67.31
```

</li>
<li>Get Example Visibility of Asteroids (Hardcoded Coordinates):
Example request:
  
```bash
GET https://asteroidcatcher-db7fb1c99a25.herokuapp.com/visibility
```

</li>
