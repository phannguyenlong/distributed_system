CREATE TABLE student (
    name VARCHAR(50),
    id VARCHAR(50),
    year VARCHAR(50),
    gender VARCHAR(50)
);

CREATE TABLE book (
    name VARCHAR(50),
    author VARCHAR(50)
);

CREATE TABLE newspaper (
    name VARCHAR(50),
    type VARCHAR(50)
);

INSERT INTO student (name, id, year, gender) VALUE ('aaa', 'bbb', '2000', 'female');

select * from student;

