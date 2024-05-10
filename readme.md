
---

# CodeHack Application

## Description

CodeHack is an application that manages user accounts for a coding contest. It provides endpoints to register new users, retrieve user details, update user scores, and delete users from the contest.

## Technology Stack

- **Java**: Programming language used for backend development.
- **Spring Boot**: Framework for building Java-based applications.
- **Spring MVC**: Framework for building web applications in Java.
- **JUnit 5**: Testing framework for writing unit tests in Java.
- **Mockito**: Mocking framework for unit tests in Java.

## Endpoints

- **GET /users**: Retrieve a list of all registered users.
- **GET /users/{userId}**: Retrieve the details of a specific user.
- **POST /users**: Register a new user to the contest.
- **PUT /users/{userId}**: Update the score of a specific user.
- **DELETE /users/{userId}**: Deregister a specific user from the contest.

## Usage

### Register a new user

```http
POST /users
Content-Type: application/json

{
    "userId": "123",
    "username": "user123",
    "score": 80
}
```

### Retrieve all users

```http
GET /users
```

### Retrieve a specific user

```http
GET /users/{userId}
```

### Update a user's score

```http
PUT /users/{userId}
Content-Type: application/json

{
    "userId": "123",
    "score": 90
}
```

### Delete a user

```http
DELETE /users/{userId}
```

## Response Codes

- **200 OK**: Successful request.
- **201 Created**: User successfully registered.
- **400 Bad Request**: Invalid request data.
- **404 Not Found**: User not found.

---

