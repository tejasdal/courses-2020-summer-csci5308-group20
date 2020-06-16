Delimiter $$

DROP PROCEDURE IF EXISTS spDeleteQuestion $$
CREATE PROCEDURE spDeleteQuestion(IN id bigint)
BEGIN
Delete FROM QuestionOption WHERE QuestionOption.QUESTION_ID=id;
Delete FROM Question WHERE Question.ID=id;
END $$
DELIMITER ;
