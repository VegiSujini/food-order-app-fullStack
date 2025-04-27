
# 🍔 Food Order App

A full-stack web application for placing food orders, updating their status, uploading an image for the order, and downloading the image back.

This project is built using:

Backend: Spring Boot

Frontend: React.js

Database: H2 in-memory database

File Uploads: Multipart file handling (Spring Boot)

File Storage: Images are saved locally on disk

---

## 📁 Project Structure

```
food-order-app/
├── backend/
│   └── food-order-app/
│       ├── src/
│       ├── uploads/ (for uploaded images)
│       ├── pom.xml
│       └── application.properties
└── frontend/
    ├── src/
    ├── package.json
    └── public/
```

---

## 🚀 How to Run

### 1. Backend (Spring Boot)

#### Prerequisites:
- Java 21
- Maven

#### Steps:
```bash
cd backend/food-order-app
mvn spring-boot:run
```

✅ The backend will start at: **http://localhost:8080**

- H2 Console: `http://localhost:8080/h2-console`
- Database: In-memory (`H2`)
- Upload Directory: Files will be upload from:  
  `F:/projects-full-stack/food-order-app/backend/food-order-app/uploads/`

> Make sure the above `uploadDir` path exists or is configurable.

---

### 2. Frontend (React.js)

#### Prerequisites:
- Node.js
- npm

#### Steps:
```bash
cd frontend
npm install
npm start
```

✅ The frontend will start at: **http://localhost:3000**

---

## ✨ Features

- **Place a New Order**  
  Fill out a simple form and submit the customer's food order.

- **View All Orders**  
  A table showing all placed orders.

- **Update Order Status**  
  Update order status (e.g., from `Placed` ➔ `Preparing` ➔ `Delivered`).

- **Upload Image for Order**  
  Upload an image for a specific order.

- **Download Order Image**  
  Download the image that was uploaded for an order.

---

## 🛠️ Backend API Endpoints

| Method | Endpoint | Description |
|:-------|:---------|:------------|
| POST | `/api/orders` | Place a new order |
| GET | `/api/orders` | Get all orders |
| GET | `/api/orders/{id}` | Get a specific order |
| PUT | `/api/orders/{id}/status` | Update order status |
| POST | `/api/orders/{orderId}/upload-image` | Upload an image |
| GET | `/api/orders/{orderId}/download-image` | Download the uploaded image |

---

## ⚙️ Backend Configuration

**`application.properties`** (important configs):

```properties
# Database Config
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA Config
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

# File Upload Config
file.upload-dir=F:/projects-full-stack/food-order-app/backend/food-order-app/uploads
spring.servlet.multipart.location=F:/projects-full-stack/food-order-app/backend/food-order-app/uploads
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

---

## 📝 Important Notes

- Ensure the upload folder path (`file.upload-dir`) is correctly set and writable.
- Downloading images in React is handled via Blob object and link creation.
- The backend downloads will automatically create temporary files before serving.

---

## 📸 Screenshots (Example)


 ![image](https://github.com/user-attachments/assets/ce5bb690-e088-4dc6-8909-f0093e4b633a)
 ![image](https://github.com/user-attachments/assets/7a39f62c-5943-458d-90ad-ceb8266faed7)
 ![image](https://github.com/user-attachments/assets/d948f723-048d-451f-ac27-055c619ce97e)

---

## ✍️ Author

> Built with ❤️ SUJI ❤️ using **Spring Boot** + **React.js**.
