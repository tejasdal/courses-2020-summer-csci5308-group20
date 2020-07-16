Delimiter $$

DROP PROCEDURE IF EXISTS spAddQuestionToSurvey$$

CREATE PROCEDURE spAddQuestionToSurvey (IN surveyid int(11), IN questionid bigint(20))
BEGIN
    INSERT INTO SurveyQuestions VALUES(0, surveyid, questionid);
END$$
DELIMITER ;
