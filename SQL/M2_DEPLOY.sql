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
