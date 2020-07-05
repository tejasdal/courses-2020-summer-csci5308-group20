CREATE TABLE Survey (
  id INT NOT NULL AUTO_INCREMENT,
  courseid BIGINT(20) NOT NULL,
  status TINYINT NOT NULL,
  PRIMARY KEY (id));

ALTER TABLE Survey
  ADD CONSTRAINT survey_course_id
  FOREIGN KEY (courseid)
  REFERENCES Course (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE Survey
  ADD CONSTRAINT unique_course_id
    UNIQUE (courseid);

CREATE TABLE SurveyQuestions (
  id INT NOT NULL AUTO_INCREMENT,
  surveyid INT NOT NULL,
  questionid BIGINT(20) NOT NULL,
  PRIMARY KEY (id,surveyid, questionid),
  INDEX questionid_idx (questionid ASC),
  CONSTRAINT surveyid
  FOREIGN KEY (surveyid)
  REFERENCES Survey (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT questionid
  FOREIGN KEY (questionid)
  REFERENCES Question (ID)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);