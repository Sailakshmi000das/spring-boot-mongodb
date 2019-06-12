created muzix service using spring boot.
 1.Completed all the endpoints for CRUD operations on Muzix
 2. Used h2-console to view in-memory data
 3. Add an endpoint to search trackByName. Understand @Query and parameter passing to@Query
 4. Generated API documentation using Swagger 2
 5. Created custom exceptions TrackNotFoundException, TrackAlreadyExistsException in acom.stack....exceptions package.
    Perform appropriate exception handling and propagationBack.
 6. Running Logic on Startup in Spring.
    Created seed data to pre-fill the database with trackinformation whenever the application starts.
    Used both approaches:Approach
    1: ApplicationListener<ContextRefreshedEvent>Approach
    2: CommandLineRunner (Find out how it differs from ApplicationRunner)
 7.Global exception using Controller advice
 8.Removed all hard coded data from the application code to application.propertiesa)by using
    a)@Value.
    b) @PropertySourcec)
 9) Added @Lombok(https://drive.google.com/file/d/1QQpEQZbDD9pmW2qrhYsx5N9XYQ5bJ5dM/view)
 10) Provided a second implementation of MuzixService.
     Named it TrackServiceImpl1 and
     used @Primary and
          @Qualifier annotations for the required implementation.