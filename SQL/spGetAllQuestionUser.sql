Delimiter $$

DROP PROCEDURE IF EXISTS spGetAllQuestionUser $$
CREATE PROCEDURE spGetAllQuestionUser(IN user_id bigint,IN sort_type varchar(100))
BEGIN
select * from Question where Question.USER_ID = user_id;
END $$
DELIMITER ;
