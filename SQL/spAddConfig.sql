DELIMITER $$

DROP PROCEDURE IF EXISTS spAddConfig $$

CREATE PROCEDURE spAddConfig (
	IN key_str VARCHAR(100),
    IN value_str VARCHAR(100)
)
BEGIN
	INSERT INTO AdminConfig(CONFIG_KEY, CONFIG_VALUE)
    VALUES (key_str, value_str);
END $$

DELIMITER ;