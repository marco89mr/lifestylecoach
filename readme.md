Life Style Coach SDE Project
==============================
#### Marco Robol

The LSC project is an infrastructure of web services that allow users to keep tracking of their progresses in various daily activities.

It allow the final user to
- modify his own profile information
- insert, edit and delete new measure of a specific activity
- define goals about specific measure that can be
  - recursive
  - based on a specific value of the measures in the selected interval: fisrt, last, max, min, average, sum
  - refers to the actual value or to incremet of the value between subsequent slot of time
- being notified about progress of the goals with messages containing famous quotes
- self-manage a to-do list
- get statistic over long periods of time of all the datas in the system

-----------------------------
## Infrastructure
The infrastructure is composed by 5 services.

### LSC local database
    > REST  
    > Port: **5900**

It is a pure CRUD service that keep datas in a sqlite database.

### LSC storage
    > REST  
    > Port: **4000**

It is the only to communicate with the local database and with the external adapter. It manage CRUD request and with a little of logic keep consistency in the database, aggregate some data, and alter some post request.

### LSC andrux
    > REST  
    > Port: **1000**

It is an external adapter that fetch data from a public web service. It only expose a single GET endpoint for requesting a random quote.

### LSC business logic
    > SOAP  
    > Port: **3333**

Here it is all the logic. Entity are created and values are computed in order to evaluate goals, statistic and create notification. 
It checks for modified measures and re-compute goal progress and status.
It starts goal and re-starts recursive goals. At each of these operations it generate some notification for the user. 

### LSC final UI
    > REST  
    > Port: **8000**

It is the top-level process centric module. It requires authentication parameters at each request. It calls the storage service and the business logic service subsequently in appropriate ways.


-----------------------------
## API Documentation
Services documentation available on [Apiary](https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md)
- [LSC local database](http://docs.lsclocaldatabase.apiary.io/#)
- [LSC storage](http://docs.lscstorage.apiary.io/#)
- [LSC andrux](http://docs.lscandruxadapter.apiary.io/#)
- [LSC business logic](http://docs.lscbusinesslogic.apiary.io/#)
- [LSC final UI](http://docs.lscfinalui.apiary.io/#)


-----------------------------
## Packages documentation
All packages of all five services have been organized in a precise structure, in order to be able to share them among projects where needed.

### REST and SOAP generic packages
Packages that are available to be used by all services of the infrastructure. They are generic utilities and classes (like models of the datas) that live across all levels.

##### lsc.utils
Generic utilities classes for faster and more organized developing.

##### lsc.rest.model
Models for each resource (Not really used only but REST services like the name could let think of).

##### lsc.rest.filter
Filter: Specify queries filters available for each resource. Used by lsc.*.logic (to process requests on server side) and lsc.*.rest.client (to compose requests on client side).

##### lsc.rest.client
ClientUtils: generic methods for starting an http connection and offering standardized ways of communication, specifically ideated for REST services.

### Specific REST packages (*: finalinterface, storage, localdatabase, adapter)

##### lsc.*
    App
    Main launcher of the REST service application.     
    
    MyApplicationConfig
    Configuration for the main launcher.

##### lsc.*.logic
Implementation of all the functionalities of the application.

    BaseLogic
    generic method for each type of resource     

    FinalInterfaceLogic
    List of static field, one for resource, pointing each to his specific logic class.

    Others
    Each resource does not implements his own methods. They extends BaseLogic class and implement only some configuration function that are needed from the generic methods in order to make them work in the specific context. These configurations refers also to the classes in the packages: lsc.rest.filter and lsc.rest.model.

##### lsc.*.resources
Mapping of the urls, starting from the root, ”MainResource”, down to each resource. Each path is associated to a functionality that is implemented in the lsc.*.logic package.

##### lsc.*.rest.client
FinalInterfaceClient: Methods and subclasses for accessing remotely to the application through his REST services. It is organized in classes for each resource, each implementing a personal configuration and extending a generic class that offers generic methods: BaseClient.

### Specific SOAP packages (*: businesslogic)
Services that have been developed in SOAP technology share this organization of sub packages. Actually there is only one service implemented in SOAP in the whole infrastructure.

##### lsc.*.endpoint
LSCPublisher: Configuration and launcher for the SOAP server application.

##### lsc.*.logic

##### lsc.*.ws

    LSCLogic
    Definition of all the endpoints for the services.   
    
    LSCLogicImpl
    Implementation of each method defined in LSCLogic.

##### lsc.*.client

    JaxWsHandlerResolver
    boh     
    
    JaxWsLoggingHandler
    mah

### Contributors
This project have been completely developed by **Marco Robol** for the exam of *Introduction to Service Design Engineering* - Master in Informatics - University of Trento - 2015.



