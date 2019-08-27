# etnetera-javascriptframework-davidmonc

- [etnetera-javascriptframework-davidmonc](#etnetera-javascriptframework-davidmonc)
  * [Description](#description)
  * [Solution](#solution)
    + [How to build JavaScript Framework application](#how-to-build-javascript-framework-application)
    + [How to run JavaScript Framework application](#how-to-run-javascript-framework-application)
    + [Swagger client](#swagger-client)
      - [Monitoring - operation handler](#monitoring---operation-handler)
    + [H2 in-memory DB](#h2-in-memory-db)

## Description

[zadani.txt](zadani.txt)

## Solution

### How to build JavaScript Framework application

JDK 12 is needed. Run command:
`./gradle build`

### How to run JavaScript Framework application

JDK 12 is needed. Run command:
`./gradle bootRun`

### Swagger client

Fully working simple swagger client to call implemented java-script-controller. (defined as Docket api bean in [JavaScriptFrameworkApplication.java](/home/monckdav/workspaces/etnetera/src/main/java/com/etnetera/hr/JavaScriptFrameworkApplication.java))

http://localhost:8080/swagger-ui.html#/java-script-framework-controller

#### Monitoring - operation handler

Bean which handles operation endpoints. (defined under `management` in [application.yaml](src/main/resources/application.yaml))

http://localhost:8080/swagger-ui.html#/operation-handler

### H2 in-memory DB

To be able to check content of in-memory DB. (defined under `spring.h2.console` in [application.yaml](src/main/resources/application.yaml))

http://localhost:8080/h2

```
JDBC URL: jdbc:h2:mem:javascriptframeworks
User Name: sa
Password: 
```


