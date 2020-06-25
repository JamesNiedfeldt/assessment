# Coding Assessment
This repository contains two separate projects: the backend and frontend coding assessments. This readme details how to build and run each of them separately.
---
## Backend
The backend uses the [Spring framework](https://spring.io) to implement a RESTful API for taking and keeping notes.

To build and run the backend, navigate to `backend/` and if you have [Maven](https://maven.apache.org/) installed, run `mvn clean spring-boot:run`. If you do not have Maven installed, you can instead run `./mvnw clean sprin-boot:run`.

When the server is running, the API server will be running at http://localhost:8080. Note that the server does not connect to a database and instead uses a temporary JPA repository, so notes will be deleted upon shutting down the server.

The server can take the following requests:

**Add new note**
* URL: `/api/notes`
* Method: `POST`
* Path params: none
* Body params: `body` - the note text
* Query params: none
* Example: `curl -i -H "Content-Type: application/json" -X POST -d '{"body" : "Pick up milk!"}' http://localhost:8080/api/notes`

**Get list of notes**
* URL: `/api/notes`
* Method: `GET`
* Path params: none
* Body params: none
* Query params: `query` - Optional, returns only those notes whose body contains the query. If not supplied the request will return all saved notes.
* Example: `curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/api/notes?query=milk`

**Get note by id**
* URL: `/api/notes/:id`
* Method: `GET`
* Path params: `:id` - the integer id of the note to get
* Body params: none
* Query params: none
* Example: `curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/api/notes/1`

**Replace a note**
* URL: `api/notes/:id`
* Method: `PUT`
* Path params: `:id` - the integer id of the note to replace
* Body params: `body` - the new note text
* Query params: none
* Example: `curl -i -H "Content-Type: application/json" -X PUT -d '{"body" : "Pick up bread!"}' http://localhost:8080/api/notes/1`

**Delete a note**
* URL: `api/notes/:id`
* Method: `DELETE`
* Path params: `:id` - the integer id of the note to delete
* Body params: none
* Query params: none
* Example: `curl -i -H "Content-Type: application/json" -X DELETE http://localhost:8080/api/notes/1`
---
## Frontend
The front end uses AngularJS to get all non-pull-request issues logged in the [Angular GitHub repo](https://github.com/angular/angular) within the last seven days and display them in an HTML page. By clicking an issue in the list a more detailed version of the issue will be displayed.

The frontend requires the [AngularCLI tool](https://cli.angular.io/) to run. To install it, use npm to run `npm install -g @angular/cli`. When this is done, navigate to `frontend/` and run `npm install` to download the necessary Node modules. To run the server, run `ng serve` then open http://localhost:4200 in your browser.