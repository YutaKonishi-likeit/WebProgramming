CREATE DATABASE userManagement DEFAULT CHARACTER SET utf8;
USE userManagement;
CREATE TABLE user(id SERIAL, login_id varchar(255) UNIQUE NOT NULL, name varchar(255) NOT NULL, birth_date DATE NOT NULL, password varchar(255) NOT NULL, create_date DATETIME NOT NULL, update_date DATETIME NOT NULL);
INSERT INTO user (login_id, name, birth_date, password, create_date, update_date) VALUES('admin','ä«óùé“','1995-04-13','password','2013-03-14 03:14:15','2016-06-16 06:16:16');
SELECT * FROM user;