Delimiter $$

DROP PROCEDURE IF EXISTS spGetAllInstructorQuestionsUsingCourseId$$

CREATE PROCEDURE spGetAllInstructorQuestionsUsingCourseId (IN course_id int(11), IN survey_id int(11))
BEGIN
    select * from Question
    where USER_ID in (select UserID from CourseRole where courseID = course_id and roleID = 4)
    and
    ID not in (select questionid from SurveyQuestions where surveyid=survey_id);
END$$

DELIMITER ;