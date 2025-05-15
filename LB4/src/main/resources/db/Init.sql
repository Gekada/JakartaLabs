CREATE TABLE groups (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    group_id INTEGER REFERENCES groups(id) ON DELETE SET NULL
);

WITH created_groups AS (
    INSERT INTO groups (name) VALUES ('КН1'), ('КН2')
    RETURNING id, name
)
INSERT INTO students (name, group_id, email)
SELECT
    CASE name
        WHEN 'КН1' THEN 'Іван'
        WHEN 'КН2' THEN 'Петро'
    END AS student_name,
    id,
    CASE name
        WHEN 'КН1' THEN 'ivan@gmail.com'
        WHEN 'КН2' THEN 'petro@gmail.com'
    END AS student_email
FROM created_groups;