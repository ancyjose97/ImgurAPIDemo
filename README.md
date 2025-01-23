# ImgurAPIDemo

A Spring Boot application that integrates with the Imgur API for image upload, retrieval, and deletion. The application also includes user registration and authentication features.

## Features
- Upload images to Imgur.
- Retrieve image details from Imgur.
- Delete images from Imgur.
- User registration with encrypted passwords.
- User authentication.

## Technologies
- Spring Boot
- Spring Security
- JPA with H2 Database
- RestTemplate for external API calls
- Mockito and JUnit for testing

## Installation

1. Clone the repository:
2. Navigate into the project directory:
3. Run the application
## Configuration

Set the following environment variables or update the `application.properties` file:

- `imgur.client-id`: Your Imgur API client ID.

### Endpoints

#### Image Endpoints
- **POST** `/images/upload`: Upload an image.
- **GET** `/images/{imageId}`: Get image details.
- **DELETE** `/images/{imageId}`: Delete an image.

#### User Endpoints
- **POST** `/users/register`: Register a new user.
- **POST** `/users/login`: Login with a username and password.

## Running Tests

Run the tests using Maven
