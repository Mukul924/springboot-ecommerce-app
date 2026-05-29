![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-green)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![Spring Security](https://img.shields.io/badge/Security-SpringSecurity-success)
![Thymeleaf](https://img.shields.io/badge/Frontend-Thymeleaf-purple)
![Maven](https://img.shields.io/badge/Build-Maven-red)

# 🛒 Spring Boot Ecommerce Application

A full-stack Ecommerce Web Application built using Spring Boot, Spring Security, Thymeleaf, Hibernate, and MySQL.

## 🚀 Features

### Customer Features
- User Registration & Login
- Browse Products
- Add to Cart
- Buy Now
- Checkout
- Order Tracking
- Payment Module

### Seller Features
- Seller Dashboard
- Add Product
- Update Product
- Delete Product
- Manage Orders
- View Revenue

### Security Features
- Spring Security Authentication
- Role Based Access Control
- BCrypt Password Encryption

---

## 🛠 Tech Stack

| Technology | Used |
|------------|------|
| Java | ✅ |
| Spring Boot | ✅ |
| Spring Security | ✅ |
| Hibernate / JPA | ✅ |
| MySQL | ✅ |
| Thymeleaf | ✅ |
| Bootstrap | ✅ |
| Maven | ✅ |
| Git & GitHub | ✅ |


## 🚀 Run Locally

### Clone Repository

```bash
git clone https://github.com/Mukul924/springboot-ecommerce-app.git
```

### Create Database

```sql
CREATE DATABASE your_db_name;
```

### Update application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run Application

```bash
mvn spring-boot:run
```

Open:

```text
http://localhost:9090
```


## 🏗 Architecture

```text
Browser
   ↓
Controller Layer
   ↓
Service Layer
   ↓
Repository Layer
   ↓
MySQL Database
```



## 📂 Project Structure

```text
src/main/java
│
├── controller
│   ├── UserController
│   ├── ProductController
│   ├── CartController
│   ├── OrderController
│   └── PaymentController
│
├── service
├── repository
├── entity
├── security
└── config
```



## 📂 Project Modules

### User Module
- Registration
- Login
- Authentication

### Product Module
- Product Management
- Image Upload

### Cart Module
- Add To Cart
- Remove From Cart

### Order Module
- Place Order
- Seller Orders
- Order Status Tracking

### Payment Module
- COD
- UPI
- Card Payment Simulation

---

## 📸 Screenshots

### Login Page

![Login](screenshots/login.png)

---

### Home Page

![Home](screenshots/home.png)

---

### Seller Dashboard

![Seller Dashboard](screenshots/seller-dashboard.png)

---

### Cart

![Cart](screenshots/cart.png)

---

### Checkout

![Checkout](screenshots/checkout.png)

---

### Payment Gateway

![Payment](screenshots/payment.png)

---

### Orders Page

![Orders](screenshots/orders.png)

---

## 🎯 Future Enhancements

- Razorpay Integration
- PDF Invoice Generation
- Email Notifications
- Product Search & Filters

---

## 👨‍💻 Author

Mukul Dixit
