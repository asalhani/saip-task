# SAIB Exercise Task

This repository contains the code and artifacts for the requested task exercise after the interview. 

### Process Workflow
(asalhani - add image to wf)
I've designed the process to demonstrate, as much as I can, my expertise in designing using BPMN. Core concepts used:
-	I used Camunda forms to capture the process data in the initial step, as well as to correct data and display error event message details.
-	I used scripting as an activity (Calculate products' Total) and in variable mapping (Update CRM status (HTTP Connector)).
-	I implemented error handling in multiple ways to showcase the different possible methods.
-	I used different methods to consume the service task on the backend (as Java class - as Delegate expression). The latter is better for ease of maintenance.
-	I implemented HTTP-Connector to show an example of how an external system can be called/integrated directly from Camunda without the need to develop backend. (Update CRM status (HTTP Connector)).

### Backend Design
-	I created a simple but clean structure in the backend. In real life, it would be more sophisticated than this. However, I needed to show the core aspects.
-	I implemented a couple of Java delegates to consume the external tasks.
-	I provided a POST API to mock one of the requested systems, and I consumed this API directly from the process by using the HTTP Connector.
-	I followed the clean code approach by not injecting the names of the external tasks, variables, etc., directly. Instead, I maintained them in a configuration class for ease of change in the future.
-	I used IoC to show my understanding of the dependency injection pattern.
-	I used JSON serialization and deserialization while dealing with Camunda variables and mocked service responses.

# Repository Overview
The repository holds the code for a SpringBoot application. 
When the application starts, the following components will be initiated:
-	SpringBoot Web API exposed through `/api`.
-	Embedded Camunda 7 engine` (V. 7.21.0)`
-	H2 file system database to be used by Camunda engine to persist data between executions.

## Prerequisites
 -	Java SDK 17

## Installation
-	Download the JAR file from the "Release" section
-	Execute the command:
```shell
 java -jar OrderProcessingApp-1.0.0-SNAPSHOT.jar
```

## Exercise Implementation Details
### Configuration
I've created 2 configuration for the application:

**1. application.properties file: (asalhani)**

It has 2 section, Database/storage configuration:


        # Spring Boot DataSource configuration
        spring.datasource.url=jdbc:h2:file:./data/orderprocessingdb
        spring.datasource.username=admin
        spring.datasource.password=admin
        spring.datasource.driver-class-name=org.h2.Driver
    
        # H2 Console configuration
        spring.h2.console.enabled=true
        spring.h2.console.path=/h2-console


------------

    # Camunda configuration
    camunda.bpm.database.type=h2
    camunda.bpm.database.schema-update=true
    camunda.bpm.filter.create=All
    
    # Camunda admin user configuration
    camunda.bpm.admin-user.id=admin
    camunda.bpm.admin-user.password=admin


**2.   process.xml file (asalhani)**

This configuration will be used by Camunda on startup to deploy the process (workflow) and supported artifacts(scripts/forms/...).

### Solution Stracture (12 - asalhani)
|  Folder | Description   |
| :------------ | :------------ |
| controllers |Contains **OrderController** which acts as a mock for the CRM system. The controller is triggered/called by the **Update CRM status (HTTP Connector)** service task, which is implemented as **HTTP-Connector**.
| dto |Contains the POCO classes for API and process variables. For process variables, I used them to deserialize process variable **OrderDetails**, created after submitting the order form. With this method, I was able to work with the variable in a strongly typed manner instead of pure JSON, which is error-prone.
|  Interfaces |  Contains the interfaces I used to show the clean architecture style of coding. These interfaces have been implemented later in the **Service** layer. The interfaces have been injected later into Controllers and external task consumers using **Autowired** annotation.|
|  Services | Implementaion of interfaces.   |
|  serviceTaskConsumers  | Contains the implementation of service tasks defined in the process. All the consumers are implementing **JavaDelegate** interface. For some of them, I used `@Component()` to show that there is multiple ways to refrence consumers in process file.   |
|  shared  | Contains common functionality shared accross consumers. Also it contains the constant string for process variables refrnced in code and the BPMN error codes being thrown to the process  |
|  Resources/OrderProcessingWorkflow.bpmn  | This is the process file which will be deployed during application startup   |
|  Resources/forms  | these are **Camunda JSON forms** being refrenced in the process file in User tasks activity. Forms will be deployed along with the process during application startup. Also, during development, a form can be changed and deployed manually from **Camunda modeler** seperatly so that you can see the change immediatly.  |
|  Resources/META-INF/process.xml  | contains the Camunda engine configuration. I used this file to let Camunda know about the process and forms need to be deployed.   |





### Startup

When the application starts, the Camunda engine will start up. Camunda will start deploying the resources as per the mentioned instructions. Please note that only the changed resources will be deployed on the next application startup.

### Start Process
1. Navigate to[ Camunda Tasklist](http://localhost:8080/camunda/app/tasklist " Camunda Cockpit")
2. User name and password is admin/admin
3. Click **Start process** link
4. Select **Order Placement Process** 
5. Click **Start** button
6. The workflow now starts. Visit **Cockpit** app to check the process.
7. Complete first user task named **Fill order details** by clicking the **all filter** in Camunda tasklist app. See the following section for form filling cases.
8. Click **Save** then **Complete** buttons to submit and close the task.
9

### Order Details Task Filling Guidelines
Since I do not have the actual services requested in the task, and building a static mock API will not allow the reviewer to test several paths in the workflow, and building a dynamic mocking API will take more time. As a solution, using some of the inputs in the forms, like the total count of added products, and the total sum of product prices, I built the logic in the workflow to navigate paths using the mentioned parameters. 
Below I will explain the input and the expected execution path for each one.

##### Success path
Fill the form as per screenshot (01 - asalhani).
Make sure that:
> 
- **Order Id** does not start with number 9, since I used 9 to trigger an error event later in the process to show the concept of general error handling.
- **Number of added Products** should be greater than 1 (1 will be used as validation in backend API to trigger BPMN error exception)
- **Total sum of price** should not exceed **100** (> 100 will trigger error event later in the process)

After all the service activites finished, the workflow should stop on **Correct email address**. I've created this step to show how backend can throw/raise BPMN error and in the process, we can handle it. So, open the user task, review the error and complete it.  You will notice that I'm opening the same form submitted before, but this time on **Update** mode where only **Email** field can be modified. This is to show how to make the form dynamic based on data set on the process. Complete the user task by entering a valid email address, you will notice the process completed.

##### Terminate Process Based on Data Condition
Fill the form as per screenshot (02 - asalhani).
Make sure that:
> 
- **Order Id** does not start with number 9
- **Number of added Products** should be 1
- **Total sum of price** should not exceed **100** (> 100 will trigger error event later in the process)

After submitting the task, you will notice that the process instance got completed. This is because the **default** path of **Excelusive gateway** has been excuted because the variable `IsOrderFulfilled` set on backend in `CheckInventoryAvailability`  consumer was false. (screenshot 3 - asalhani). Also check **Send task** `Check inventory availability` how the implementation configured (using `Java class`)

##### Raise Error Event and Direct Workflow to Alternative Path
Fill the form as per screenshot (04 - asalhani).
Make sure that:
> 
- **Order Id** does not start with number 9
- **Number of added Products** should be 0
- **Total sum of price** should not exceed **100** 

After submitting the task, you will notice that execution moved to **Check Error** user task (05 - asalhani). This is because in backend, BPMN error `ProductsCountInvalid` was triggred. Now open the task, you should see the error message and code (06 - asalhani). Complete the  **Check Error**, open the new task **Fill order details** and add some products and cont.

##### Handle Undefined BPMN Error Code Globally
Fill the form as per screenshot (07 - asalhani).
Make sure that:
> 
- **Order Id** does not start with number 9
- **Number of added Products** should be 2 or more
- **Total sum of price** should be greater than **100** 

After submitting the task, you will notice that execution moved to **Event sub-process** `Global Error Handler` (08 - asalhani). This showes how unhandled BPMN exception raised from backend can be handled and identified in the process to give more visibility and control over the process. Complete the **Check Error** user task the process will be completed. 

##### Service Task Implemented using HTTP-Connector + Error Handling in From Script
Fill the form as per screenshot (09 - asalhani).
Make sure that:
> 
- **Order Id** MUST start with number 9
- **Number of added Products** should be 2 or more
- **Total sum of price** should not exceed **100** 

After submitting the task, you will notice that execution moved to **Event sub-process** `Global Error Handler` (09 - asalhani). If you check **Update CRM status (HTTP Connector)** it's implemented as `http-connector`. The error is being thrown when the response is received from backend **OrderController - update-order-status API**. When the response returned as anything other than 200 (OK) then the script output binding will throw BPMN error. Hence there is no catch for that error, global error handling will be activiated in the process. (10 - 11 - asalhani).