# Feed Me!

## Format:

- `/feedme`, contains the file structure of the back end REST api
- `/feedme-client`, contains the Vue.js front-end client
- `/docs`, contains current UML diagrams and introductory documents

## Required setup to run the project:

### Back-end steps:

- The project uses postgreSQL architecture, this means a local database is required: `createdb feedme`
- `application.properties` must be created. An example copy is given at `/feedme/src/main/resources`
  - For the most part this can be copied to the same directory without the `.example` extension
  - In order for database connections to be successful the following values ay need to be changed to match the info of the created database and it's owner on the system.
    - `spring.datasource.url=jdbc:postgresql://localhost:5432/feedme`
    - `spring.datasource.username=postgres`
    - `spring.datasource.password=root`
- Once the database exists it should be sufficient to run the main driver class in `FeedmeApplication.java`

### Front-end steps:

- Currently it is sufficient to route into `/feedme-client` and:
  - run `npm install` to pull the required dependencies
  - run `npm run serve` to spin up a development version of the client

## Current state of the project and the things that are worth looking at:

### Back-end:

**Most get routes work to a moderate degree**

* `/ingredients` can be queried for a list of ingredients that currently exists in the database
* `/recipes` can be queried with multiple parameter options (adding a `?` and separating options with `&`):
    - `limit`: Integer value representing how many records should be shown
    - `offset`: Integer value representing how many records should the result be offset by
    - `sort`: String value representing a column to order the records by, currently this can be:
        * calories
        * carbs
        * fats
        * proteins
    - `mincalories`: Integer value representing the minimum amount of calories a record can have
    - `maxcalories`: Integer value representing the maximum amount of calories a record can have
    - `mincarbs`: Integer value representing the minimum amount of carbs a record can have
    - `maxcarbs`: Integer value representing the maximum amount of carbs a record can have
    - `minproteins`: Integer value representing the minimum amount of protein a record can have
    - `maxproteins`: Integer value representing the maximum amount of protein a record can have
    - `minfats`: Integer value representing the minimum amount of fats a record can have
    - `maxfats`: Integer value representing the maximum amount of fats a record can have
* `/recipes/{id}` can be queried to obtain a specific recipe in long format
* `/users/me` can be queried for current user info ( if user is logged in )
* `/users` can be queried by admins for a simplified list of existing users
* `/users/{id}` can be queried by admins for information about a specific user

**The user post routes are fully functional**

* `/users/register` can be posted to with the required information:
    - `username`
    - `password`
    - `email`
    - `admin` ( OPTIONAL ) - this will generally be ignored and default to false
* `/users/login` can be posted to with the following information to obtain a JWT login token:
    - `username`
    - `password`
* Test users for these accounts are the following:
    - `username`: admin, `password`: 1234567890
    - `username`: johndoe, `password`: 1234567890

**TODO stuff:**

* All PATCH routes for the purpose of editing recipes/comments/reviews
* POST routes for adding comments and reviews ( and potentially new recipes )
* DELETE routes for removing entities ( most of the repository and service functions exist but have not been connected )

### Front-end

**Currently under construction, not worth reviewing specifically.**



