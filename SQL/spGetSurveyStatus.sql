Delimiter $$
DROP PROCEDURE IF EXISTS spGetSurveyStatus$$

CREATE PROCEDURE spGetSurveyStatus (IN survey_id int(11))
BEGIN
select status from Survey where id = survey_id;
END$$
DELIMITER ;
