**Database Event Monitor**
 
**Documentation**
 
 This is a Spring Boot based application for monitoring db interactions (INSERT, UPDATE, DELETE), through WebSocket and ActiveMQ.
 The dedicated table for monitoring called SAMPLE_DATA. This is a simple table, just for demonstrating the workflow.
 Database is a embedded H2 Database, that initialize when application starts.
 
**Workflow**

 You have to open http://localhost:8080/index.html, where you automatically subscribe on websocket.
 
 After a db action it triggers a jms message, and send a websocket message for the clients who subscribed for the topic.
 
 On clients it displays a messaage about the db action.

**Basic Endpoints**  

 /version - Shows the application version  
 /status - Shows that the application started.

**Manual testing**

 For manual testing created some endpoints. The main goal was to do these tests without necessity any third party application.
 
 These endpoints are:
 /insert/{text}      - Insert a row with the given {text}  
 /delete/{id}        - Delete a row with the given {id}  
 /update/{id}/{text} - Update row with {id} and set {text}  
 
**Build**  
 In project directory: 
 mvn clean package  
 java -jar target/action-monitor-0.0.1-SNAPSHOT.jar
 
**Logs**  

Log files are located under application directory logs/ folder
 
