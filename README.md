# MythBustersBackend

This is the backend project for MythBusters, built with Spring Boot and PostgreSQL. It uses Flyway for database migrations and requires a PostgreSQL database setup with a specific user and database. Also creates database and user with a script.

## Prerequisites

- **Java 17 or later**: Ensure Java is installed. Run `java -version` to verify.
- **Maven**: Required to build and run the project. Run `mvn -version` to check.
- **PostgreSQL 13 or later**: The database server must be installed and running.
- **Python 3.x**: Needed to run the `create_database.py` script.
- **Git**: To clone the repository.

## Setup Instructions

### 1. Install PostgreSQL
Install PostgreSQL on your system:

- **Ubuntu/Debian**:
  ```bash
  sudo apt update
  sudo apt install postgresql
  ```
- **macOS** (using Homebrew):
  ```bash
  brew install postgresql
  ```
- **Windows**: Download and install from the [official PostgreSQL website](https://www.postgresql.org/download/windows/).

Start the PostgreSQL service:
```bash
sudo service postgresql start  # Linux
# or
brew services start postgresql  # macOS
# On Windows, use the Services panel or pgAdmin.
```

### 2. Configure the PostgreSQL Admin User
The default PostgreSQL admin user is `postgres`. Set a password for it:
1. Log in to PostgreSQL:
   ```bash
   psql -h localhost -U postgres
   ```
2. Set a password:
   ```sql
   ALTER USER postgres WITH PASSWORD 'your_password_here';
   ```
   Replace `your_password_here` with a secure password. You'll need this for the next step.

### 3. Set Up the Database and User
The project requires a database named `myth_busters_db` and a user named `username` with password `password`. The provided `create_database.py` script automates this process.

1. Install the required Python library:
   ```bash
   pip install psycopg2-binary
   ```
2. Run the script:
   ```bash
   python3 create_database.py
   ```
3. Follow the prompts:
   - Enter the PostgreSQL host (default: `localhost`).
   - Enter the port (default: `5432`).
   - Enter the admin user (e.g., `postgres`) and password (set in Step 2).
   - Enter the new `username`.
   - Enter the new `password` for the created user.
4. The script will:
   - Create the `username` user with password `password` (if it doesn't exist).
   - Create the `myth_busters_db` database with `username` as the owner.
   - Update the `application.properties` file with the correct database connection settings.

### 4. Configure the Application
Ensure the `application.properties` file contains:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/myth_busters_db
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=none
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

### 5. Verify Flyway Migrations
The project uses Flyway for database migrations. Ensure the `src/main/resources/db/migration` directory contains at least one migration file (e.g., `V1__init.sql`). Example:
```sql
CREATE TABLE IF NOT EXISTS test_table (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
```

If you encounter Flyway errors, reset the migration history:
```sql
psql -h localhost -U username -d myth_busters_db
DROP TABLE IF EXISTS flyway_schema_history;
```

### 6. Build and Run the Project
1. Install dependencies:
   ```bash
   mvn clean install
   ```
2. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

The application should start without errors, connecting to the `myth_busters_db` database.

## Troubleshooting
- **"FATAL: database 'username' does not exist"**:
  - Ensure the `create_database.py` script uses `database="postgres"` for the admin connection (see the script provided in previous conversations).
  - Verify the PostgreSQL server is running and the `postgres` database exists.
- **Flyway errors**:
  - Check that `src/main/resources/db/migration` contains valid `.sql` files.
  - Reset the Flyway history if needed (see Step 5).
- **Connection issues**:
  - Verify the PostgreSQL server is running (`sudo service postgresql status`).
  - Check `pg_hba.conf` for correct authentication settings (e.g., `host all all 127.0.0.1/32 md5`).
