# Bank application (Work in progress)

A full-stack banking management application built with Spring Boot, Angular, Spring Security, and JWT. The application enables administrators to manage customers, bank accounts, and banking operations through a secure REST API and a modern web interface.

## Features
* Customer Management (CRUD)
* Bank Account Management
* Current and Savings Accounts
* Debit and Credit Operations
* Money Transfers
* Search Customers and Accounts
* Secure Authentication with JWT (coming soon)
* RESTful API
* Swagger/OpenAPI Documentation
* AI chatbot (coming soon)
* Dashboard with Charts and Statistics (coming soon)

## Tech stack
### Backend
* Java 17
* Spring Boot 4.0.7
* Spring Web MVC
* Spring Data JPA
* Hibernate
* H2 Database (development)
* MySQL (production)
* Lombok
* SpringDoc OpenAPI (Swagger UI)
* Maven
### Frontend
* Angular 
* Bootstrap 5

## Project roadmap
* ✅ Springboot backend
* ✅ REST API
* ✅ Spring security and Json Web Token
* ✅ Angular frontend
* ⏳ Chatbot AI
* ⏳ Other functions

## Screenshots

### Login interface
![login page](./screenshots/LoginPage.png)

### Admin interface
#### Navbar
![Admin navbar view](./screenshots/admin/AdminNavbar.png)
#### Customer list
![Customers list](./screenshots/admin/Admin_CustomersList.png)
#### Accounts list
![Accounts list](./screenshots/admin/AdminAccountsList.png)
#### Operations list
![Operations list](./screenshots/admin/AdminOperationsList.png)
#### Operations list empty
![Operations list empty](./screenshots/admin/AdminOperationsMenuEmpty.png)
#### Add customer
![Add customer](./screenshots/admin/AddCustomerMenu.png)
#### Add accounts
![Add current account](./screenshots/admin/AddCurrentAccount.png)
![Add saving account](./screenshots/admin/AddSavingAccount.png)
#### Account operations
|Credit|Debit|Transfer|
|------|-----|--------|
|![Credit](./screenshots/admin/CreditMenu.png) | ![Debit](./screenshots/admin/DebitMenu.png) | ![Transfer](./screenshots/admin/BankTransferMenu.png)|

### User interface
#### Navbar
![User navbar view](./screenshots/user/UserNavbarList.png)
#### Customers list
![Customers list](./screenshots/user/UserAccountList.png)
#### Accounts list
![Accounts list](./screenshots/user/UserAccountList.png)
#### Operations list
![Operations list](./screenshots/user/UserOperationList.png)
#### Not authorized
![Not authorized](./screenshots/user/UserNotAuthorized.png)



## License
This project was developed for educational purposes and demonstrates the implementation of a secure banking management system using Spring Boot and Angular.