CREATE DATABASE IF NOT EXISTS student_db;

USE student_db;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    age INT NOT NULL,
    grade VARCHAR(10) NOT NULL
);

-- Sample Data
INSERT INTO students (name, email, age, grade) VALUES 
('John Doe', 'john@example.com', 20, 'A'),
('Jane Smith', 'jane@example.com', 22, 'B');
