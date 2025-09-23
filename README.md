# Blog Serve

**Blog Serve** is a RESTful blog backend built with Spring Boot. It enables users to register, authenticate, and manage articles and comments in a secure, modular fashion. The application is designed for quick setup and extensibility, and is deployed on AWS EC2 for reliable cloud access.

## Features

- **User Authentication**: Secure signup and login with JWT-based authentication.
- **Article Management**: Create, read, update, and delete blog articles.
- **Commenting System**: Add and manage comments on articles.
- **Role-Based Access**: Only authenticated users can create/edit/delete their own articles and comments.
- **Seed Data**: On start, the app seeds users, articles, and comments for demo/testing.
- **Cloud Deployment**: Deployed on AWS EC2 for scalable and remote access.

## API Endpoints

- `POST /users` — Register a new user
- `POST /users/login` — User login
- `GET /articles` — List all articles
- `GET /articles/{slug}` — Get article by slug
- `POST /articles` — Create a new article (auth required)
- `PUT /articles/{id}` — Edit an article (auth required)
- `DELETE /articles/{id}` — Delete an article (auth required)
- `POST /articles/{id}/comments` — Add a comment (auth required)
- `DELETE /comments/{id}` — Delete a comment (auth required)

## Tech Stack

- **Backend**: Java, Spring Boot, Spring Security, ModelMapper
- **Authentication**: JWT (JSON Web Tokens)
- **Database**: JPA/Hibernate (configurable)
- **Deployment**: AWS EC2

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/siddharth1201/blog-serve.git
   cd blog-serve
   ```

2. **Configure your database**  
   Update `application.properties` for your DB connection.

3. **Build and run**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API**
   - Localhost: `http://localhost:8080`
   - (Or your AWS EC2 public DNS)

## Deployment

This project is deployed on AWS EC2. To deploy your own instance:
- Provision an EC2 instance (Amazon Linux/Ubuntu recommended).
- Install Java (17+) and Maven.
- Clone this repo and run the Spring Boot service.
- Open necessary ports (e.g., 8080) in your security group.



**Author:** [siddharth1201](https://github.com/siddharth1201)
