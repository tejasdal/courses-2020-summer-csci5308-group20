Delimiter $$

DROP PROCEDURE IF EXISTS spCreateQuestion $$

CREATE PROCEDURE spCreateQuestion (IN in_id bigint, IN in_title varchar(100), IN in_description VARCHAR(300), IN in_user_id bigint, IN in_question_type_id int, IN in_create_at date)
BEGIN
    INSERT INTO Question(ID, TITLE, DESCRIPTION, TYPE_ID, USER_ID, CREATED_AT) VALUES(in_id, in_title, in_description, in_question_type_id, in_user_id, in_create_at);
END$$
DELIMITER ;
