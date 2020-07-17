Delimiter $$
DROP PROCEDURE IF EXISTS spUnpublishSurvey$$

CREATE PROCEDURE spUnpublishSurvey (IN survey_id int(11))
BEGIN
update Survey set status=0 where id = survey_id;
END$$
DELIMITER ;