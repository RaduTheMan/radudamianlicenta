# Gemixque

Gemixque is a full-stack web application that recommends video games based on a recommendation engine with collaborative filtering. Briefly, the recommendation was implemented using a weighted arithmetic mean, where the weights were calculated with an adaption of the Pearson correlation coefficient.

For more details about the implementation of this project, you can check the documentation inside the *lucrare-scrisa* folder, the *Damian Radu lucrare de licenta.pdf* file.

## Technologies used
- Database: Neo4j

- Back-end: Spring Boot

- Front-end: Angular

## Demo
[Link demo youtube](https://youtu.be/aeDOddUIm8Q)

## Installation & usage

- Database

1. Download the [Neo4j desktop application](https://neo4j.com/download/).

2. Create a new project inside the application like in the picture below:

![new project](https://i.imgur.com/xJXogML.png)

For convenience, this project can be named Gemixque.

3. Add a new database in the project previously created:

![new database](https://i.imgur.com/kK4PlSR.png)

The name 'Gemixque', a password of your choice and the version 4.4.2 should be set.

![add database](https://i.imgur.com/rprpeIU.png)

4. Open the terminal like in the picture below to import the dump files *neo4j* and *system* from the *dumps* folder:

![terminal](https://i.imgur.com/SkyiqYN.png)

The command used to import the dump files is described below:

```bash
.\bin\neo4j-admin.bat load --from=*path-to-neo4j-dump-file* --database=neo4j --force
.\bin\neo4j-admin.bat load --from=*path-to-system-dump-file* --database=system --force
```
5. Run the database and check if the data is successfully imported.

![open](https://i.imgur.com/VLZUJrE.png)

![verify](https://i.imgur.com/jW0TD0x.png)

- Back-end

For convenience, open the Spring Boot project from the *gemixque-api* with your preferred IDE. Before running the back-end using the *GemixqueApiApplication.java* file, find the *application.properties* file and change the password with the one set when creating the database.

![credentials](https://i.imgur.com/xW9NBVq.png)

- Front-end

The front-end project is located inside the *gemixque-ui* folder. Inside the root folder, run the following commands:

```bash
npm i
npm start
```

After all the steps were completed, a new tab should open in the browser with the home page.

To login, you can use an account from the *users_original.csv* file in order to get the recommendations.


