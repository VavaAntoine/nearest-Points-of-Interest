# Nearest Points of Interest (POI) Application

This project is built using the following technology stack:

- **JDK 17**
- **Jakarta EE 10**
- **Apache TomEE Plume 10.0.0-M2**
- **MySQL 8.0**

---

## 1. JDK 17
- **Version**: JAVA 17.0.7 (Release Date: April 18, 2023)
- **Download Link**: [Oracle JDK 17 Archive](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

---

## 2. IDE: Apache NetBeans
- **Version**: Apache NetBeans 17
- **Download Link**: [NetBeans 17 Download Page](https://netbeans.apache.org/front/main/download/nb17/)

---

## 3. Apache TomEE Plume
- **Version**: Apache TomEE 10.0.0-M2
- **Download Link**: [Apache TomEE Plume 10.0.0-M2](https://www.apache.org/dyn/closer.cgi/tomee/tomee-10.0.0-M2/apache-tomee-10.0.0-M2-plume.zip)

### Configuration:
1. Open **NetBeans 17**.
2. Navigate to **Services** > **Servers** > **Add Server**.
3. Choose **Apache Tomcat or TomEE**.
4. Set the **Server Location** to the folder containing **apache-tomee-plume-10.0.0-M2**.

---

## 4. MySQL Connector/J
- **Version**: MySQL Connector/J 9.0.0
- **Download Link**: [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/?os=26)

### Configuration:
1. Place the **mysql-connector-j-9.0.0.jar** file in the `...\apache-tomee-plume-10.0.0-M2\lib` folder.
2. Edit the `tomee.xml` file located in the `...\apache-tomee-plume-10.0.0-M2\conf` directory.
3. Add the following resource within the `<tomee>` tags:

    ```xml
    <tomee>
        <Resource id="jdbc/MyDatabase" type="DataSource">
            JdbcDriver = com.mysql.cj.jdbc.Driver
            JdbcUrl = jdbc:mysql://localhost:3306/poi_db
            UserName = ********
            Password = ********
            JtaManaged = true
        </Resource>
    </tomee>
    ```

---

## 5. Database Setup
- Execute the SQL script found at `...\nearestpoi\poiScripts.sql` in your MySQL database to set up the **Points of Interest (POI)** schema.

---

## 6. API Endpoints

The application provides two main API endpoints:

1. **Get Popular Points of Interest**  
   Returns all points of interest with a request count greater than the specified value.
   - **Endpoint**: `GET /api/v1/pois/popular`
   - **Example Request**:
     ```http
     http://localhost:8080/api/v1/pois/popular?count={count-value}
     ```
   - **Query Parameters**:
     - `count` (integer): The minimum number of requests a point of interest must have to be returned.
  
2. **Get Nearest Point of Interest**  
   Returns the nearest point of interest to the specified latitude and longitude.
   - **Endpoint**: `GET /api/v1/pois/nearest`
   - **Example Request**:
     ```http
     http://localhost:8080/api/v1/pois/nearest?latitude={latitude-value}&longitude={longitude-value}
     ```
   - **Query Parameters**:
     - `latitude` (double): The latitude of the location.
     - `longitude` (double): The longitude of the location.

---

## How to Run

1. Ensure **MySQL** is running with the necessary credentials and the POI database is properly configured.
2. Start **Apache TomEE** with the **NetBeans IDE** and deploy the application.
3. Use the provided API endpoints to interact with the POI service.
