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

## Design Notes (Short design notes on trade-offs, assumptions, and “next steps” if given more time.)


## FE Design Notes

### Trade-Offs / Assumptions
- Very quick Scaffolding. I didn't do any work Responsive, ADA, or enough fault tolernance.. Way more robust and less componentized than Id like.
- Styling was not a big consideration. Typically Ill build up more common components/styling.
- Redirect countdown was for display purposes.
- Assuming data coming through would be of a MVP size. Graphs and charts won't scale.
- Assuming data won't timeout (No retry implemented)
- Assuming no Authentication/Authorization.
- Production site would need more side-case and variable validation.

### Next Steps
- Fully Testing SiteWide Error Boundary + 404. (Project wasn't catching like it should, so there is some general cases that could be refactored out)
- Unit Testing primary flows (ran out of time)
- Interfaces more solidly defined for objects being passed around.
- Adding Paging/Limiting strategy to handle any amount of real traffic.
- Husky + Prettier.
- Storybook for common components
- Tree Shaking for Barrel Files
__
## BE Design Notes

### Trade-Offs / Assumptions
- Didn't implement a migration script. Handled by boot setup. (See: Next Steps)
- I like fail/fast approach with boundaries.. But when not in a crunch, would take a look at important boundaries for more defensive programming and recovery.
- Dual Entity/Model classes couple BE/FE. Only done here for time. (You can create a thin BE like this, but unless purposeful, it's a design smell)
- Didn't implement fk constraint between models. Domain didn't require additional complexity (See: Next Steps)
- Didn't use ID for some operations. Simpler to stick to only one identifier (term) (See: Next Steps)
- Very thin Controller/Service/Repository barrier. Repositories are fine, but I didn't bother being TOO careful about what was in the Controller Layer.
- Created only one properties file. Would need to create additional .yml and/or allow intake of properties on run for deploying to any environment.
- Somewhat fast and loose with date formats and BE <> FE contract trust. (See: defensive programming and recovery)

### Next Steps
- Would implement a managed migration framework.
- Would define perhaps more rigorous Req/Resp Entities with more safeguards around invalid data.
- Create proper relational DB models. Create FK. Use ids for operational unique-ness.
- Would like to deploy the whole stack with Docker.
- Need to have a better proxy setup.
