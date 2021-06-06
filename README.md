# Job-Scheduler
1. Prerequisite
     -  Use Postgresql, Create a New Databse and add name of database in `application.properties`
     -  Create table from `initial_architecture.sql` from resources;
     -  use this website to create cron expression when you want to run the JOB https://www.freeformatter.com/cron-expression-generator-quartz.html
        
2.  Start this Application
    -  `mvn clean install`
    -  `mvn spring-boot:run`
    
3.  Create Job config
    -   endpoint : `localhost:8080/jobs`
    -   Request Type : POST
    -   Body :      
           ```
                   {
                     "title":"JOB-121-Create-Invoice",
                     "cronExpression": "0/10 * * ? * * *"
                 } 
        ```
     - Response :
     ```
        {
            "id":2,         // auto generated 
            "title": "JOB-121-Create-Invoice",
            "cronExpression": "0/10 * * ? * * *",
            "createdAt": "2021-06-06T10:20:51.304+0000",
            "updatedAt": "2021-06-06T10:20:51.304+0000"
        }
     ```

      - This is a cron expression run job periodically in 10s;

4. Fetch list of all JOB COnfig;
    -   endpoint : `localhost:8080/jobs`
    -   Request Type : GET
    -   Response :
     ```
        [
            {
                "id": 1,
                "title": "latest",
                "cronExpression": "0/10 * * ? * * *",
                "createdAt": "2021-06-06T09:28:33.676+0000",
                "updatedAt": "2021-06-06T09:28:33.676+0000"
            },
            {
                "id": 2,
                "title": "JOB-121-Create-Invoice",
                "cronExpression": "0/10 * * ? * * *",
                "createdAt": "2021-06-06T10:20:51.304+0000",
                "updatedAt": "2021-06-06T10:20:51.304+0000"
            },
            {
                "id": 3,
                "title": "JOB-1-Create-Dispathces",
                "cronExpression": "0/10 * * ? * * *",
                "createdAt": "2021-06-06T10:32:04.436+0000",
                "updatedAt": "2021-06-06T10:32:04.436+0000"
            }
        ]
     ```    
5.  Start A newly created Job
    -   endpoint : `localhost:8080/jobs`
    -   Request Type : PUT
    -   Params : `?title=JOB-1-Create-Dispathces`   

6. Corner Cases;
    -   Server goes down while job is running. =>> Maintaining Job Log which will indicate whether a job was successfully finished or not
    -   Parallel execution of jobs ==>> can define number of threads by configuring `org.quartz.threadPool.threadCount` in `quartz.properties`
    -   If the execution takes longer than expected ==>> can define timeout in milliseconds for a particular thread by configuring `jobs.timeout` in `application.properties`