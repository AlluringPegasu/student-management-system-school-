CREATE TABLE departments (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(50)
);

CREATE TABLE students (
	student_id INT PRIMARY KEY,
	name VARCHAR(50),
	email VARCHAR(50),
	dept_id INT,
	FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
);


INSERT INTO departments (DEPT_ID, DEPT_NAME) VALUES (1, 'Computer Science');
INSERT INTO departments (DEPT_ID, DEPT_NAME) VALUES (2, 'Mathematics');
INSERT INTO departments (DEPT_ID, DEPT_NAME) VALUES (3, 'Business Administration');
INSERT INTO departments (DEPT_ID, DEPT_NAME) VALUES (4, 'Mechanical Engineering');
