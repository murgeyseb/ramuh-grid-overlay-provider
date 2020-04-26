# Project Ramuh grid overlay provider microservice

This repository contains a microservice application that provides a GeoJSON overlay based on
an input grid file.
Following input formats are currently available on this application:
- XIIDM: PowSyBl internal data model.
- UCTE-DEF: ENTSO-E legacy exchange standard.
- CGMES: ENTSO-E current exchange standard.

This project is a work in progress. For more information about Project Ramuh in general, please refer to the [project prologue post](https://murgeyseb.github.io/project/ramuh/2020/04/23/project-ramuh-prologue.html).

## Building the app

Start by downloading project sources:

```bash
git clone https://github.com/murgeyseb/ramuh-grid-overlay-provider.git
```

Build the application with Maven:

```bash
cd ramuh-grid-overlay-provider
mvn install
```

Run the server:

```bash
java -jar target/ramuh-grid-overlay-provider-0.0.1-SNAPSHOT.jar
``` 

The server is now started. To check for server status, use Spring Actuator endpoints.

```bash
curl http://localhost:8080/actuator/health
```

To retrieve an overlay from an input grid file, post a request on the ```/overlay``` endpoint.

```bash
curl -F gridFile=@myLocalGridFile http://localhost:8080/overlay
```
