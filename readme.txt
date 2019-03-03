Building the application:
    "mvn clean install" or IntelliJ build

Running the application:
    "java -jar [target/MakeHRFun-0.0.1-SNAPSHOT.jar]" or IntelliJ run

Using the API (e.g. with Chrome Advanced Rest Client plugin):

       PUT  | http://localhost:8080/offers          | {"jobTitle":"Secret agent", "startDate" : "2019-01-01"}
       POST | http://localhost:8080/offers/1/apply  | {"candidateEmail":"john.travolta@world.best.email", "resumeText"="Hello"}
       GET  | http://localhost:8080/offers

       POST | http://localhost:8080/applications/1/invite
       POST | http://localhost:8080/applications/1/accept
       GET  | http://localhost:8080/applications
       GET  | http://localhost:8080/applications/count-active