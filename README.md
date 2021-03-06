# WonderLand Campsite API

Github link: https://github.com/ChaoTang1117/Wonderland
### Overview
This project is designed for a tourist campsite by providing RESTful API to manage campsite reservations.
Users can register their information, search for campsite availability, make a reservation, modify an existing reservation and cancel the reservation.
Constraints are introduced to validate reservation logics.
The service is able to handle concurrent requests and large volume of requests.
In a real world environment, requests can be lost during http calls if the service is down immediately after triggering the requests.
In this case, kafka can be incorporated to temporarily store the requests. Different HTTP method calls can be published to a separate kafka topic and then read by consumer and store into the dbs.


### How to test
The project is swagger enabled. After starting the application, all the endpoints can be accessed through swagger UI :http://localhost:8080/swagger-ui/index.html#/
To access database, use url: http://localhost:8080/h2console/
The database is H2 Embedded. JDBC url should be jdbc:h2:mem:testdb as defined in application.properties

### Detail:

#### Use Case: Search campsite availability
Users can search what date(s) the campsite is available for visiting.
- Endpoint: /search
- Method: GET
- Parameter: startDate, endDate


#### Use Case: User registration
Before making reservation, Users should firstly register themselves to the campsite by providing their information
- Endpoint: /registrations
- Method: POST
- Request body:
```javascript
 {
  "lastName": "string",
  "firstName": "string",
  "emailAddress": "example@hotmail.com"
  }
 ```
- Response: userId (UUID)

#### Use Case: Create Reservation
After user is registered, the user is able to make a reservation with their intended arrival date and departure date
- Endpoint: /reservations
- Method: POST
- Path Variable: userId (UUID)
- Request body: 
```javascript
  {
  "arrivalDate": "2022-06-19",
  "departureDate": "2022-06-19"
  }
```
- Response: bookingId (UUID)

#### Use Case: Modify Reservation
Once user created a reservation, the user is able to change the reservation with a new arrival date and departure date as long as the capacity is allowed
- Endpoint: /reservations
- Method: PUT
- Path Variable: bookingId (UUID)
- Request body:
```javascript
  {
  "arrivalDate": "2022-06-19",
  "departureDate": "2022-06-19"
  }
```

#### Use Case: Cancel Reservation
Once user created a reservation, the user is able to cancel the reservation with a new arrival date and departure date as long as the capacity is allowed
- Endpoint: /reservations
- Method: PUT
- Path Variable: bookingId (UUID)
- Request body:
```javascript
  {
  "arrivalDate": "2022-06-19",
  "departureDate": "2022-06-19"
  }
```
#### Database Design:
The project uses H2 embedded in-memory relational database integrated with Spring Boot.

- availability_info
    - date: Date(Primary Key)
    - capacity: VarChar


- user_info
    - user_id: VarChar UUID(Primary Key)
    - last_name: VarChar
    - first_name: VarChar
    - email: VarChar

- reservation
    - booking_id: VarChar UUID(Primary Key)
    - user_id: VarChar
    - arrival_date: Date
    - departure_date: Date
