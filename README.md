# PokeApiOracleConnector

## Context
Fastly made and not well optimised. 
I created this tool to download some data coming from the [PokéApi](https://pokeapi.co/) and insert it on a Oracle database for a bachelor course.
The data then used to create a Pokemon Manager app using [Apex](https://apex.oracle.com/en/)

## Installation

### 1. Clone the Repository
To get started, first clone the repository to your local machine. Open a terminal and run the following command:

```bash
git clone https://github.com/NoeBerdoz/PokeApiOracleConnector.git
```

### 2. Install the dependencies
The project uses Maven for dependency management. To install the dependencies simply run the mvn command or use your code editor helper.

### 3. Configure Oracle Database Connection
Copy past the exemple properties file [database.properties.exemple](https://github.com/NoeBerdoz/PokeApiOracleConnector/blob/78849de136d283fb4412fb663e8edcb91e752c07/database.properties.exemple) to a new file named **database.properties**
```
cp database.properties.exemple database.properties
```

Edit **database.properties** and replace your_host, your_port, your_service_name, your_username, and your_password with your actual database connection details.

### 4. Prepare the database schema with SQL script
On your Oracle database, run the SQL script [database_script_create.sql](https://github.com/NoeBerdoz/PokeApiOracleConnector/blob/78849de136d283fb4412fb663e8edcb91e752c07/database_script_create.sql)
This will create the required tables for the insertions of the data.

## Usage
You can launch the program by running [Main.java](https://github.com/NoeBerdoz/PokeApiOracleConnector/blob/78849de136d283fb4412fb663e8edcb91e752c07/src/main/java/ch/nb/Main.java)

Currently the scripts will retrieves all the Pokemon from the first generation (<=151). You can change the number of Pokemon that are retrieves there by editing the [Main.java](https://github.com/NoeBerdoz/PokeApiOracleConnector/blob/78849de136d283fb4412fb663e8edcb91e752c07/src/main/java/ch/nb/Main.java) file.

**Caution: As the script makes a lot of requests to the API, please avoid spamming it.**

