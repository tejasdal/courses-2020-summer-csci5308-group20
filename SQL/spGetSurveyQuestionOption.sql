DELIMITER $$
CREATE PROCEDURE `spGetSurveyQuestionOption`( IN in_question_id BIGINT)
BEGIN
    SELECT id, question_id, options, value from QuestionOption where question_id = in_question_id;
END$$
DELIMITER ;