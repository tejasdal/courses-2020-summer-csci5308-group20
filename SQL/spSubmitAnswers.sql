CREATE PROCEDURE spSubmitSurvey (IN surveyId bigint(20), IN banner varchar(20), IN questionId bigint(20), IN answerValue varchar(300))
BEGIN
SET @userId = (Select id from User where bannerID = banner);
insert into SurveyAnswers(surveyId,questionId,userId,answerValue) values(surveyId,questionId,@userId,answerValue);
END$$
DELIMITER ;