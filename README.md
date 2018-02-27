# iFood Music API

Welcome to the iFood music API. This application will help you to retrieve the coolest song depending of the current weather. The API exposes two Restful endpoints to retrieve music:

* Retrieve by location name: `http://localhost:8080/playlist?location=lima`
* Retrieve by location coordinates: `http://localhost:8080/playlist?longitude=-23.550520&latitude=-46.633309`

## Deployment
The API use a Redis database to store user's credentials. You are allowed to use any Redis distribution you want, the most easy way its deploy a Docker container with Redis installed:

`docker run --name redis  -p 6379:6379  -d redis`

However, ensure you have correctly configured the Redis connection credentials in the `application.yml` file:

```
spring:
  redis:
    host: <your redis host name/url>
    port: <your redus port>
```

Then, just enter to the root application dir and execute the gradle bootRun command:

`./gradlew clean build bootRun`

### Executing unit tests
Unit test can be executed running the following command:

`./gradlew test`


### Executing integration tests
The integration test could be executed in an isolated environment (even without internet connection). Execute the gradle task to run all integration tests:

`./gradlew intTest`


### Automated deployment
Since the steps are very easy to execute, there is a nicer way to deploy the entire app running just one command. We will use docker compose for this purpose. Feel free to modify the docker-compose.yml configuration file accord your business needs.
Execute this command in the root application folder:

`docker-compose up --project-name ifood -d`

This command will load both services, Redis and the API. Note that -d flag indicates this process will run i background, so you will be able to enter inside each container with the following command:

`docker exec -it <container-id> /bin/bash `

You can find more information about the docker compose commands in the official documentation: https://docs.docker.com/compose/

## Documentation
You can review in detail any framework/tool used in this project through the architecture design records. 
Find it here:` doc/arch/`






