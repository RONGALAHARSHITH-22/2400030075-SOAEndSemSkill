# Round Robin Load Balancing with Spring Boot

This Maven multi-module project demonstrates an API Gateway distributing ride requests across three Driver Service instances.

## Requirements

- JDK 17 or later
- Maven 3.8+
- Internet connection for the first Maven dependency download

## Project modules

- `driver-service`: one Spring Boot REST service, run three times on ports 8081, 8082 and 8083.
- `api-gateway`: Spring Cloud Gateway on port 8080, using Spring Cloud LoadBalancer's round-robin algorithm.

## Run on Windows PowerShell

Open four PowerShell terminals in this project folder.

### Terminal 1: Driver instance 1

```powershell
cd driver-service
$env:PORT="8081"
mvn spring-boot:run
```

### Terminal 2: Driver instance 2

```powershell
cd driver-service
$env:PORT="8082"
mvn spring-boot:run
```

### Terminal 3: Driver instance 3

```powershell
cd driver-service
$env:PORT="8083"
mvn spring-boot:run
```

### Terminal 4: API Gateway

From the project root:

```powershell
cd api-gateway
mvn spring-boot:run
```

Wait until all four applications have started.

## Verify the distribution

In another PowerShell terminal, run:

```powershell
1..9 | ForEach-Object {
    Invoke-RestMethod http://localhost:8080/ride
}
```

Expected response sequence:

```text
Ride processed by Driver Instance: 8081
Ride processed by Driver Instance: 8082
Ride processed by Driver Instance: 8083
Ride processed by Driver Instance: 8081
Ride processed by Driver Instance: 8082
Ride processed by Driver Instance: 8083
Ride processed by Driver Instance: 8081
Ride processed by Driver Instance: 8082
Ride processed by Driver Instance: 8083
```

Each instance should receive 3 of the 9 requests.

## Build both modules

From the project root:

```powershell
mvn clean package
```

## Notes

- The gateway uses `lb://driver-service`; Spring Cloud LoadBalancer selects the next instance using round robin.
- This lab uses a static instance list for clarity. The three driver processes must be running before testing.
- If a port is already in use, stop the other process or change the configured port in `DriverConfig.java` and the matching service launch command.
