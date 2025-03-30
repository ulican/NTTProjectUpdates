# NTTProjectUpdates

A Spring Boot application for managing tasks and notes.

## 🛠 Technologies Used

- Java 17+
- Spring Boot 3.3.5
- Spring Data JPA
- Spring Security + JWT
- PostgreSQL
- Maven

## ⚙️ Local Setup

1. Make sure PostgreSQL is installed and running.
2. Create a database named `task` and a user with:
   - Username: `alex`
   - Password: `alex1234`

3. In the `application.properties` file:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/task
    spring.datasource.username=alex
    spring.datasource.password=alex1234
    ```

4. Run the application from IntelliJ or via Maven:
    ```bash
    ./mvnw spring-boot:run
    ```
   The app will start on `http://localhost:8080`.

## 🧪 Testing

Unit tests are located in `src/test/java`.

Run tests with:
```bash
./mvnw test
