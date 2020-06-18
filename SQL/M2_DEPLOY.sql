create table AdminConfig(
	CONFIG_KEY varchar(100) not null,
	CONFIG_VALUE varchar(100) not null,
	primary key(CONFIG_KEY)
); 

create table PasswordHistory(
	USER_ID BIGINT not null,
	UPDATED_TIME BIGINT not null,
	PASSWORD varchar(76) not null,
	primary key(USER_ID, UPDATED_TIME),
	foreign key(USER_ID) references User(ID)
);

CREATE TABLE QuestionType(
    ID INT PRIMARY KEY, 
    Type VARCHAR(50)
);

CREATE TABLE Question(
    ID bigint NOT NULL,
    TITLE VARCHAR(100) NOT NULL,
    DESCRIPTION VARCHAR(300) NOT NULL,
    TYPE_ID INT NOT NULL,
    USER_ID BIGINT NOT NULL,
    CREATED_AT DATE NOT NULL,
    PRIMARY KEY(ID),
    FOREIGN KEY(TYPE_ID) REFERENCES QuestionType(ID),
    FOREIGN KEY(USER_ID) REFERENCES User(ID)
);

CREATE TABLE QuestionOption(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    QUESTION_ID BIGINT NOT NULL,
    OPTIONS VARCHAR(200) NOT NULL,
    VALUE INT NOT NULL,
    PRIMARY KEY(ID),
    FOREIGN KEY(QUESTION_ID) REFERENCES Question(ID)
);


INSERT INTO QuestionType
VALUES
    (1, 'numeric'),
    (2, 'multiple-choice-choose-one'),
    (3, 'multiple-choice-choose-many'),
    (4, 'free-text');