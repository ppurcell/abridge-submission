# Global READme

## Client Setup
1. npm install
2. npm start
3. Open [http://localhost:3000](http://localhost:3000) to view it in the browser.
4. Make sure your backend server is running at http://localhost:5000
5. Enjoy!

## Server Setup
1. Build Gradle `./gradlew build`
2. Spin up the postgreSQL instance  `docker compose -f dev-docker-compose.yml up`
3. (Optional) You can run your own instance of PostgreSQL and update the `application.yml` file with your database credentials.
4. Run the application `./gradlew bootRun`
5. (Optional) You can seed the database with sample data by running `./gradlew seedDb` (Task defined in `build.gradle`)
