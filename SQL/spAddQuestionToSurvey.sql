Delimiter $$

DROP PROCEDURE IF EXISTS spAddQuestionToSurvey$$

CREATE PROCEDURE spAddQuestionToSurvey (IN surveyid int(11), IN questionid bigint(20),IN criteriaType int, IN criteriaValue int)
BEGIN
    INSERT INTO QuestionCriteria VALUES(0, criteriaType,criteriaValue);
    INSERT INTO SurveyQuestions VALUES(0, surveyid, questionid, (select LAST_INSERT_ID()));
END$$
DELIMITER ;