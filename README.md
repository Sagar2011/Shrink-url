# Shrink-url
This is the repository for the instacar task round.

# Shrink-url is the site where you can generate tiny urls for the particular valid links entered by any users.Also it provides to check the graphs at home page for how many urls are associated with our site. user can go with any time to his saved urls to see his tiny urls and visit. user gets notifications when any url get expired (2 days). 

> please find anytime for suggestions and reviews. Also there are many things to do which due to less time couldn't achieve. thank you for patience and visiting.


For Running the application follow this steps:

# Requirement:
`1) Docker
 2) jdk 1.8
 3) maven-3.6
 4) node-12
 5) angular-cli-8.3
 6) Web Sockets
 
# Run the application:
  - docker-compose up -f docker-compose-local -d --build

# After running the above command check the running services using the command:
  - docker ps
 
# after checking that total 5 service and 2 database service is running follow this steps to continue:
  - docker exec -it <pid of mongoservice> bash
  - mongo -u root -p
  password: example
  - use userdatabse;
  - exit
  - exit

# Wait for minute and Visit the location: (http://localhost:80)
------------------------------------------------------------------------------------------------------------------------------
 **Once the above done the services are up and now this is the basic information as a developer purpose**
  
  - **|   PORTS      |        Service          |**
  -   |    8761      |    Eureka naming Server |
  -   |    80        |    Zuul proxy Server    | 
  -   |    9000      |      user  Server       |
  -   |    9200      |    url  Server          |
  -   |    9500      |     webapp  Server      |
  -   |    27017     |    mongo db Server      |
    
   # Branching Strategy as:
      - master (production)
        - dev  (pre-stage)
          - release-0.1.0 (developer stage)


# Live link location:
- 

# Backlog
1) Application is not responsive for mobile
2) Admin is set for specific mail id.
3) Web sockets some times failed to load.
4) Notification sometimes too slow to reach in ui.

