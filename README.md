# Order Microservice

This project uses Spring Boot with support for both **H2** and **MySQL** databases, switchable using Spring profiles.

---

## Run with H2 (default)

No setup needed. Just run the app.

---

## Run with MySQL

1. Open src/main/resources/application.properties
2. Update profile in application.properties:
      - h2 to mysql
3. Run the SQL scripts in the sql folder to set up your DB:
   - 01-create-user.sql
   - 01-shipping-table.sql