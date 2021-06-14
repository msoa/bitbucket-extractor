<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#running-locally">Running Locally</a></li>
      </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

This extractor gets data about repositories, pull requests, commits, comments and activities from the Bitbucket API. First get data from repositories by filtering by project name,
then, for each repository, it gets the pull requests, commits, comments, activities and stores them in the database as json data.

### Built With

Below listing frameworks.
* [Kotlin](https://kotlinlang.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Java Persistence API](https://www.oracle.com/java/technologies/persistence-jsp.html)
* [Flyway](https://flywaydb.org/)
* [Postgres](https://www.postgresql.org/)
* [OkHttp](https://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)

## Getting Started

This example of how you may give instructions on setting up your project locally.

### Running Locally

1. Clone the repo and build
   ```sh
   git clone https://github.com/msoa/bitbucket-extractor.git
   cd bitbucket-extractor
   ./gradlew build
   ```
2. Set the environment variables and run jar file
   ```sh
   export BITBUCKET_AUTH=<token>
   export BITBUCKET_OWNER=<owner>
   export BITBUCKET_PROJECT_NAME=<project name>
   export DB_HOST=<host>
   export DB_USER=<user>
   export DB_PASSWORD=<pass>
   export DB_PORT=<port>
   java -jar build/libs/bitbucket-extractor-0.0.1-SNAPSHOT.jar
   ```

### Running docker-compose

1. Execute Docker Compose
    ```sh
    docker-compose run -e BITBUCKET_AUTH=<Basic Authentication Token> -e BITBUCKET_OWNER=<Owner> -e BITBUCKET_PROJECT_NAME=<Project Name> bitbucket-extractor
    ```

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->
## License

Distributed under the MIT License. See [MIT](./LICENSE) for more information.

<!-- CONTACT -->
## Contact

[Marco Sérgio de Oliveira Araújo](marcosergio.analista@gmail.com)

Project Link: [https://github.com/msoa/bitbucket-extractor](https://github.com/msoa/bitbucket-extractor)
