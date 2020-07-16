DELIMITER $$

DROP PROCEDURE IF EXISTS spGetAllAnswerBySurveyId$$

CREATE PROCEDURE spGetAllAnswerBySurveyId(
	IN survey_id Bigint
)
BEGIN
	select questionId,userId,answerValue,answerIndex from SurveyAnswers where surveyid = survey_id;
END $$

DELIMITER ;