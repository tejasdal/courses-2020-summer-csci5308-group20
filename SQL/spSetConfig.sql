DELIMITER $$

DROP PROCEDURE IF EXISTS spSetConfig $$

CREATE PROCEDURE spSetConfig (
	IN key_str VARCHAR(100),
    IN value_str VARCHAR(100)
)
BEGIN
	UPDATE AdminConfig
    SET CONFIG_VALUE = value_str
    WHERE CONFIG_KEY = key_str;
END $$

DELIMITER ;