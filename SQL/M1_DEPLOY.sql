CREATE TABLE User (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    bannerID VARCHAR(20) NOT NULL,
    password VARCHAR(76) NOT NULL
);

CREATE TABLE UserContactInfo (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userID BIGINT NOT NULL,
    firstName VARCHAR(100) NULL,
    lastName VARCHAR(100) NULL,
    email VARCHAR(320) NOT NULL,
    FOREIGN KEY (userID) REFERENCES User(id)
);

CREATE TABLE Role (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(10)
);

CREATE TABLE Course (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200)
);

CREATE TABLE CourseRole (
	courseID BIGINT NOT NULL,
    roleID BIGINT NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (courseID) REFERENCES Course(id),
    FOREIGN KEY (roleID) REFERENCES Role(id),
    FOREIGN KEY (userID) REFERENCES User(id)
);

CREATE TABLE SystemRole (
	roleID BIGINT NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (roleID) REFERENCES Role(id),
    FOREIGN KEY (userID) REFERENCES User(id)
);

INSERT INTO Role(role)
VALUES
    ('Admin'),
	('Guest'),
    ('Student'),
    ('Instructor'),
    ('TA');

/*
	This is not how you would do this in the real world, it would not be safe to have passwords
    or accounts stored in files in git.  This creates the admin user with an empty password.
*/
INSERT INTO User(bannerID, password)
VALUES ('B-000000', '1234');

SELECT LAST_INSERT_ID()
INTO @adminID;

INSERT INTO UserContactInfo(userID, firstName, lastName, email)
VALUES (@adminID, 'Rob', 'Hawkey', 'rhawkey@dal.ca');

SELECT id
INTO @adminRoleID
FROM Role
WHERE role = 'Admin';

INSERT INTO SystemRole(roleID, userID)
VALUES (@adminRoleID, @adminID);

SELECT * FROM Role;
SELECT * FROM User;
