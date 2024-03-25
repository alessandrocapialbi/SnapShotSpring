# Photo Appointment Booking System

This project is a web application in Java Spring Boot for managing appointments for photographers. It allows clients to schedule appointments for photography sessions and provides admin operations for photographers to manage their appointments and given services/time slots.

## Features

- **Client Booking**: Clients can sign up, log in, and schedule appointments for photography sessions.
- **Admin Operations**: Photographers can log in and perform administrative tasks such as adding/removing photoshoots, adding/removing time slots, viewing, editing, and cancelling appointments.
- **Appointment Management**: The system allows for easy management of appointments, including scheduling, rescheduling, and cancellation.
- **Email Notifications**: Clients receive email notifications for appointment confirmations, reminders, and cancellations.
- **User Authentication and Authorization**: Secure user authentication and authorization using Spring Security.

## Technologies Used

- **Spring Boot**: Backend framework for building the application.
- **Spring Security**: For user authentication and authorization.
- **Spring Data JPA**: For interacting with the database.
- **MySQL**: Database management system for storing application data.
- **HTML/CSS/JavaScript**: Frontend technologies for creating the user interface.
- **Maven**: Dependency management tool for Java projects.
- **JUnit**: For unit testing.
- **MockMVC**: For REST API testing.

## Installation

1. Clone the repository: git clone https://github.com/alessandrocapialbi/SWE_photoshoot_booking/
2. Navigate to the project directory: cd your_cloned_project_directory
3. Configure MySQL database settings in docker-compose.yml or application.properties
4. Build the project using Maven: mvn clean install
5. Run the application: java -jar target/your_cloned_project_directory.jar
6. Access the application in your web browser at `http://localhost:8080`.

## Usage

1. Sign up as a client or log in as an existing client.
2. Schedule an appointment by providing necessary details.
3. Photographers can log in using their credentials to access admin operations.
4. Photographers can view, edit, or cancel appointments from the admin dashboard.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request with any improvements or features you'd like to add.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

For any inquiries or support, please contact [capialbi.alessandro@gmail.com](mailto:capialbi.alessandro@gmail.com).





