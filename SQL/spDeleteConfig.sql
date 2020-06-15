DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteConfig $$

CREATE PROCEDURE spDeleteConfig (
	IN key_str VARCHAR(100)
)
BEGIN
	DELETE FROM AdminConfig
    where CONFIG_KEY = key_str;
END $$

DELIMITER ;