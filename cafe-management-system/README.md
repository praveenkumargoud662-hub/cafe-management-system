# Cafe Management System

Beginner Java web app: Java 25 (LTS), Maven, Tomcat 9, JSP, Servlets, JDBC and MySQL.

## Features
- Dashboard: product count, order count and total sales
- Add, list, edit and delete products
- Place an order using product and quantity
- Calculate bill totals automatically
- View order history

## Requirements
JDK 25 (LTS), Maven 3.9+, Apache Tomcat 9.x, MySQL Server 8.x. MySQL Workbench and VS Code/IntelliJ are optional.

## Step 1 — Check Java and Maven
Open PowerShell:
```powershell
java -version
mvn -version
```
If Maven is not recognized, install Maven, add its `bin` folder to Windows PATH, reopen PowerShell and retry.

## Step 2 — Create the database
Start MySQL. Open MySQL Workbench, open `database/cafe_db.sql`, and execute the full script. It creates `cafe_db`, `products`, and `orders`, then inserts sample menu items.

Command-line alternative:
```powershell
mysql -u root -p
```
Or, from the project folder:
```powershell
mysql -u root -p < database/cafe_db.sql
```

## Step 3 — Set your MySQL password
Open `src/main/java/com/cafe/util/DBUtil.java`. Replace `YOUR_MYSQL_PASSWORD` with your local MySQL root password. Update `USER` if you use another account. Do not commit real credentials to GitHub; use environment variables or a secrets manager for deployment.

## Step 4 — Build
In PowerShell, change directory to the folder containing `pom.xml`, then run:
```powershell
mvn clean package
```
The WAR file will be created at `target/cafe-management-system.war`.

## Step 5 — Deploy to Tomcat 9
Copy the WAR into Tomcat's `webapps` folder. Start Tomcat on Windows:
```powershell
cd C:\path\to\apache-tomcat-9\bin
startup.bat
```
Open `http://localhost:8080/cafe-management-system/` in your browser. Change port 8080 if your Tomcat configuration uses another port. Stop Tomcat with `shutdown.bat`.

## Step 6 — Test
1. Dashboard shows product count, order count and sales.
2. Products: add a new menu item.
3. Orders & Billing: select a product and quantity, then place an order.
4. Check the order history and dashboard sales total.

## Troubleshooting
- **`mvn` not recognized:** install Maven, add Maven `bin` to PATH, reopen terminal.
- **MySQL access denied:** check `USER` and `PASSWORD` in `DBUtil.java`.
- **Communications link failure:** make sure MySQL is running on the configured host/port (default 3306).
- **HTTP 404:** verify the WAR is inside Tomcat `webapps` and Tomcat has started.
- **HTTP 500:** inspect Tomcat `logs/catalina*.log`; check database credentials and SQL setup.
- **Tomcat version:** this app uses `javax.servlet` and is intended for Tomcat 9. Tomcat 10+ uses `jakarta.servlet` and needs code changes.

## Folder structure
```text
cafe-management-system/
├── pom.xml
├── README.md
├── database/cafe_db.sql
└── src/main/
    ├── java/com/cafe/
    │   ├── dao/
    │   ├── model/
    │   ├── servlet/
    │   └── util/
    └── webapp/
        ├── css/style.css
        ├── index.jsp
        ├── nav.jsp
        ├── dashboard.jsp
        ├── products.jsp
        └── orders.jsp
```

## Understand the layers
- **Model:** Java classes that represent application data.
- **DAO:** Java classes that execute SQL and access the database.
- **Servlet:** handles browser requests and calls Java logic.
- **JSP:** renders HTML pages with dynamic data.
- **JDBC:** connects Java to MySQL.
- **Maven:** manages dependencies and builds the WAR.
- **Tomcat:** runs the web application.

This is a learning starter, not a production-ready system. Before real customer use, add authentication, role permissions, CSRF protection, stronger validation, logging, tests and secure credential configuration.
