Delimiter $$

DROP PROCEDURE IF EXISTS spCreateQuestionOption;

CREATE PROCEDURE spCreateQuestionOption ( IN in_question_id BIGINT, IN in_options VARCHAR(200), IN in_value INT)
BEGIN
    INSERT INTO QuestionOption( QUESTION_ID, OPTIONS, VALUE) VALUES( in_question_id, in_options, in_value);
END$$
DELIMITER ;