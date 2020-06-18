Delimiter $$

DROP PROCEDURE IF EXISTS spGetAllQuestionUser $$
CREATE PROCEDURE spGetAllQuestionUser(IN user_id bigint,IN sort_type varchar(100))
BEGIN
select * from Question where Question.USER_ID = user_id
order by
case when sort_type='date' then CREATED_AT end,
case when sort_type='title' then Title end;
END $$
DELIMITER ;
