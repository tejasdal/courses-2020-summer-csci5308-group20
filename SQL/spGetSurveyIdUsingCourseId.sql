Delimiter $$

DROP PROCEDURE IF EXISTS spGetSurveyIdUsingCourseId$$

CREATE PROCEDURE spGetSurveyIdUsingCourseId (IN course_id bigint(20))
BEGIN
    select id from Survey where courseid = course_id;
END$$
DELIMITER ;