Delimiter $$
DROP PROCEDURE IF EXISTS spPublishSurvey$$

CREATE PROCEDURE spPublishSurvey (IN survey_id int(11))
BEGIN
update Survey set status=1 where id = survey_id;
END$$
DELIMITER ;