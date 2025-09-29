# Getting Started

## Setting up the Environment
1. Build Gradle `./gradlew build`
2. Spin up the postgreSQL instance  `docker compose -f dev-docker-compose.yml up`
3. (Optional) You can run your own instance of PostgreSQL and update the `application.yml` file with your database credentials.
4. Run the application `./gradlew bootRun`
5. (Optional) You can seed the database with sample data by running `./gradlew seedDb` (Task defined in `build.gradle`)

## Endpoints
- [GET] /bridge-links (list all bridge links)
- [DEL]/bridge-links/{:term} (delete a bridge link by id)
- [POST] /bridge-link (create a new bridge link)
- [POST] /bridge-link/{:term} (retrieve link by term. And register an audit entry)
- [GET] /bridge-link/{:term}/analytics (retrieve analytics for a specific term)

## Frameworks/Libraries Used
- Spring Boot ()
- Spring Data JPA (for database interactions)
- PostgreSQL (database)
- Docker (for containerizing the database)
- Gradle (build tool)
- Lombok (for reducing boilerplate code)

## LLM Usage
- Almost exclusively just for prototyping the SQL group by query.