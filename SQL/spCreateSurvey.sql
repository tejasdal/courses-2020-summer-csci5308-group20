Delimiter $$
DROP PROCEDURE IF EXISTS spCreateSurvey$$

CREATE PROCEDURE spCreateSurvey (IN course_id int(11))
BEGIN
insert into Survey values (0,course_id,0);
END$$
DELIMITER ;
