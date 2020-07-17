DELIMITER $$

DROP PROCEDURE IF EXISTS spGetParticipantsOfSurvey$$

CREATE PROCEDURE spGetParticipantsOfSurvey (
	IN survey_id Bigint
)
BEGIN
SELECT U.id AS id,
		U.bannerID AS bannerID,
		U.password AS password,
        UC.firstName AS firstName,
        UC.lastName AS lastName,
        UC.email AS email
	FROM User U
    JOIN UserContactInfo UC ON (U.id = UC.userID)
    WHERE U.id in (select distinct userId from SurveyAnswers where surveyid=survey_id);
END $$

DELIMITER ;

