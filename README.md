## **RECORD SHOP API**

## **Features**
- **GET** all albums or a specific album by ID.
- **POST** to add a new album.
- **PUT** to update an existing album.
- **DELETE** an album by ID.
- Integrated with **PostgreSQL** for persistent storage.
- Includes health checks via Spring Actuator.


## **Technologies Used**
- **Java 21**
- **Spring Boot 3.4**
- **PostgreSQL**
- **Hibernate JPA**
- **Maven**
- **H2 Database** (for development and testing)

## **Setup**

### **Prerequisites**
1. **Java 21** installed.
2. **PostgreSQL** installed and running.
3. A tool **Postman** for API testing.
4. ENDPOINTS:
Endpoints
Method	      Endpoint	     Description	               Request Body
GET	         /	          Fetch all albums	                None
GET	         /{id}	      Fetch a specific album by ID	    None
POST	       /	          Add a new album	                  {"title": "...", "artist": "...", ...}
PUT	         /{id}	      Update an existing album by ID	  {"title": "...", "artist": "...", ...}
DELETE	     /{id}	      Delete an album by ID	            None

6. The API will be available at: http://localhost:8080
7. The example of JSON to post:
  {
    "title": "Overgrown",
    "artist": "James Blake",
    "genre": "ELECTRONIC",
    "stock": 10,
    "price": 17.00
 }


### **Installation Steps**
1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/record-shop-api.git
   cd record-shop-api
   
2. **Modify application-prod.properties for your database credidentials**
spring.datasource.url=jdbc:postgresql://localhost:5432/recordshopdb
spring.datasource.username=postgres
spring.datasource.password=<your_password>
spring.jpa.hibernate.ddl-auto=update
3. Run the application

   
#### **Future Enhancements**

1. Authentication and Authorisation -> Introduce user authentication with OAuth2.
2. Advanced Filtering -> Enable filtering by genres, price ranges, stock levels, or artist names to enhance search capabilities.
3. Data Sorting -> Add support for paginated responses and sorting options to improve usability with large datasets.
4. API Documentation -> Integrate tools fe. Swagger for comprehensive and interactive API documentation.


   

   
