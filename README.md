# JWebtainer

A Java-based lightweight web container designed to serve web applications with a servlet engine, offering a simplified
alternative to traditional Java EE containers.

![Build Status](https://img.shields.io/badge/build-in%20development-yellow)
![Version](https://img.shields.io/badge/version-0.1.0--alpha-blue)

## Overview
`JWebtainer` is a minimalist, Java-native web container built from the ground up using modern Java features.
`JWebtainer` focuses on servlet-based application isolation, modular web app deployment, and HTTP service management in
pure Java. It aims to provide a lightweight, embeddable alternative to full-fledged servlet containers like Tomcat or Jetty.

## Technologies

- Java 17
- Gradle
- JUnit 5

## Prerequisites

- Java JDK 17 or later
- Gradle 7.x or later
- Git

## Features

### Current Implementation
- Light-weight embedded servlet container
- Web server with HTTP/1.1 support
- HTTP request/response routing
- Static resource serving
- Gradle build and test setup

### Planned Features
- WAR-like application packaging and auto-deployment
- Custom classloader per web app
- Web application isolation
- Logging and monitoring
- Hot reload support
- Web-based admin interface
- CLI tools for deployment and management

## Get Started
1. Clone the repository:
   ```bash
   git clone
   cd JWebtainer
   ```
## Quick Start
Just run `main` method in `java/deep/jwebtainer/Main.java` an on your browser `http://localhost:8080/`

## Usage

To configure servlets in your application:

1. Create a `servlet.properties` file in your application's resources directory
2. Add servlet mappings using the format: `servletName=fully.qualified.ClassName`
   ```properties
   myServlet=com.example.MyServlet
   ```
3. Ensure your servlet class implements `HttpServlet` from the JWebtainer project
   ```java
   public class MyServlet extends HttpServlet {
       // Implement servlet methods
   }
   ```

Note: Each servlet class must implement the `HttpServlet` class provided by JWebtainer to ensure proper integration with
the container.

## Contributing
We welcome contributions to JWebtainer! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bug fix:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Make your changes and commit them:
   ```bash
    git commit -m "Add your feature description"
    ```
4. Push your changes to your fork:
5. Push to the branch:
   ```bash
   git push origin feature/your-feature-name
   ```
6. Create a pull request against the `main` branch of the original repository.
7. Ensure your code adheres to the project's coding standards and includes tests for new features or bug fixes.
8. Wait for review and address any feedback provided by the maintainers.
9. Once approved, your changes will be merged into the main branch.
10. Update the documentation if necessary, especially if you added new features or changed existing functionality.
## License
This project is licensed under the MIT License.
## Contact
For any questions, suggestions, or issues, please open an issue on the GitHub repository or contact the maintainers via email.


   


