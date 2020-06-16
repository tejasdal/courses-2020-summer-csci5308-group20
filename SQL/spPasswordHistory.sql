DELIMITER $$

DROP PROCEDURE IF EXISTS spPasswordHistory $$

CREATE PROCEDURE spPasswordHistory (
	IN banner varchar(20),
    IN policy_value INT
)
BEGIN
select PASSWORD from PasswordHistory where USER_ID=(select id from User where bannerID = banner)order by UPDATED_TIME desc limit policy_value;
END $$

DELIMITER ;