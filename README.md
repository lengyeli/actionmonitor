**Database Event Monitor**
 
**Documentation**
 
 This is a Spring Boot based application for monitoring db interactions (INSERT, UPDATE, DELETE), through WebSocket and ActiveMQ.
 The dedicated table for monitoring called SAMPLE_DATA. This is a simple table, just for demonstrating the workflow.
 Database is a embedded H2 Database, that initialize when application starts.
 
**Workflow**
 You have to open http://localhost:8080/index.html, where you automatically subscribe on websocket.
 After a db action it triggers a jms message, and send a websocket message for the clients who subscribe for the topic.
 On clients it displays a messaage about the db action.

**Manual testing**

 
