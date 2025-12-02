📌 Coupon Management System – Backend Assignment (Spring Boot + PostgreSQL)
📝 Overview

This project is a backend service that manages discount coupons and applies them to cart items.
It is built as part of the Monk Commerce Backend Developer Assignment.

The system supports multiple coupon types and exposes APIs to:

1. Create coupons

2. Retrieve applicable coupons for a given cart

3. Apply a selected coupon to update cart pricing

| Component       | Technology                   |
| --------------- | ---------------------------- |
| Language        | Java 17                      |
| Framework       | Spring Boot 3.x              |
| Database        | PostgreSQL                   |
| ORM             | Hibernate / JPA              |
| Build Tool      | Maven                        |
| Testing         | JUnit 5 / Mockito            |
| JSON Processing | Jackson                      |
| API Testing     | Postman / Swagger (optional) |



📂 Project Structure

src
├── main
│   ├── java/com.coupon_management_system
│   │    ├── controller
│   │    ├── service
│   │    ├── calculator
│   │    ├── dto
│   │    ├── repository
│   │    └── domain
│   └── resources
│        ├── application.yml
│        └── schema.sql (optional)
├── test
│   └── unit tests

⚙️ Setup & Run Instructions
1️⃣ PostgreSQL Setup

CREATE DATABASE coupon_db;

Update credentials in application.yml:

spring:
datasource:
url: jdbc:postgresql://localhost:5432/coupon_db
username: postgres
password: your_password
jpa:
hibernate:
ddl-auto: update
show-sql: true

Using IntelliJ

Right-click →
Run CouponManagementSystemApplication

📦 REST APIs
🟡 1. Create Coupon

POST /coupons

Sample Request (Cart-wise)

{
"type": "CART_WISE",
"cartWiseDetails": {
"thresholdAmount": 1000,
"mode": "PERCENTAGE",
"discountValue": 10
}
}

🟢 2. Get Applicable Coupons

POST /applicable-coupons

Request
{
"cartItems": [
{ "product_id": 1, "quantity": 2, "price": 100 },
{ "product_id": 2, "quantity": 1, "price": 200 }
]
}

Response:
{
"applicableCoupons": [
{ "id": 1, "discount": 50 },
{ "id": 2, "discount": 100 }
]
}


🔵 3. Apply Coupon

POST /apply-coupon/{id}

Response:
{
"cartItems": [
{ "product_id": 1, "quantity": 2, "price": 100, "discount": 20 },
{ "product_id": 2, "quantity": 1, "price": 200, "discount": 30 }
],
"totalPrice": 400,
"totalDiscount": 50,
"finalPrice": 350
}

| Coupon Type  | Description                             |
| ------------ | --------------------------------------- |
| Cart-Wise    | Applies discount on total cart value    |
| Product-Wise | Applies discount only on selected items |
| BxGy         | Buy X Get Y free (extension support)    |


🧪 Running Tests: 
mvn test


Includes:

Calculator tests

Service logic tests

Mockito-based unit tests

📁 Postman Collection

Available in postman_collection.json (optional)



🧑‍💻 Developer

Gaurav Kesharwani
Backend Developer
📧 gauravke2002@gmail.com
