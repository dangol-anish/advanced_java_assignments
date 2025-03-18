CREATE DATABASE StudentDB;


use StudentDB;

USE StudentDB;
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    grade VARCHAR(10)
);

INSERT INTO students (name, age, grade) VALUES ('John Doe', 21, 'A');
INSERT INTO students (name, age, grade) VALUES ('Jane Smith', 22, 'B');
