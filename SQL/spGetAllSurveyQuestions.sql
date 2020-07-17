Delimiter $$

DROP PROCEDURE IF EXISTS spGetAllSurveyQuestions$$

CREATE PROCEDURE spGetAllSurveyQuestions (IN survey_id int(11))
BEGIN
    select t2.* from SurveyQuestions as t1 left join Question as t2 on (t2.ID = t1.questionid)
    where t1.surveyid = survey_id order by t1.id;
END$$
DELIMITER ;