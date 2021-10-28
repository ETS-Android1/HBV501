# Feed Me!


## Format:

* `/feedme`, contains the file structure of the back end REST api
* `/feedme-client`, contains the Vue.js front-end client
* `/docs`, contains current UML diagrams and introductory documents


## Required setup to run the project:

### Back-end steps:

* The project uses postgreSQL architecture, this means a local database is required: `createdb feedme`
* `application.properties` must be created. An example copy is given at `/feedme/src/main/resources`
    - For the most part this can be copied to the same directory without the `.example` extension
    - In order for database connections to be successful the values of:
        * `spring.datasource.url=jdbc:postgresql://localhost:5432/feedme`
        * `spring.datasource.username=postgres`
        * `spring.datasource.password=root`
    May need to be changed to match the info of the created database and it's owner on the system.
* Once the database exists it should be sufficient to run the main driver class in `FeedmeApplication.java`

### Front-end steps:

* Currently it is sufficient to route into `/feedme-client` and:
    - run `npm install` to pull the required dependencies
    - run `npm run serve` to spin up a development version of the client


## Current state of the project: