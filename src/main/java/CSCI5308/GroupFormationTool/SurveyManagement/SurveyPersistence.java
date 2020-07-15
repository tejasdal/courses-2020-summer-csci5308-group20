package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Question.Answers;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyPersistence implements ISurveyPersistence {

    private Logger log = LoggerFactory.getLogger(SurveyPersistence.class);
    public long getSurveyIdUsingCourseId(long courseId) {
        log.trace("Fetching survey ID for a course with ID: {} from database.", courseId);
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spGetSurveyIdUsingCourseId(?)");
            proc.setParameter(1, courseId);
            ResultSet resultSet = proc.executeWithResults();
            if (resultSet != null) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
            return (-1);
        } catch (SQLException e) {
            log.error("Error while fetching survey ID for a course with ID: {} from database, error: {}", courseId, e.getMessage());
            return (-1);
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean createSurvey(long courseId) {
        log.trace("Creating a new survey for a course with ID: {} to database", courseId);
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spCreateSurvey(?)");
            proc.setParameter(1, courseId);
            proc.execute();
            return true;
        } catch (SQLException e) {
            log.error("Error while creating a new survey for a course with ID: {} to database, error: {}", courseId, e.getMessage());
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean addQuestionToSurvey(long surveyId, long questionId) {
        log.trace("Adding question with ID: {} to a survey with ID: {} in database", questionId, surveyId);
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spAddQuestionToSurvey(?,?)");
            proc.setParameter(1, surveyId);
            proc.setParameter(2, questionId);
            proc.execute();
            return true;
        } catch (SQLException e) {
            log.error("Error while adding question with ID: {} to a survey with ID: {} in database, error: {}", questionId, surveyId, e.getMessage());
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean deleteQuestionFromSurvey(Long surveyId, Long questionId) {
        log.trace("Deleting question with ID: {} from a survey with ID: {} in database", questionId, surveyId);
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spDeleteQuestionFromSurvey(?,?)");
            proc.setParameter(1, surveyId);
            proc.setParameter(2, questionId);
            proc.execute();
            return true;
        } catch (SQLException e) {
            log.error("Error while deleting question with ID: {} from a survey with ID: {} in database, error: {}", questionId, surveyId, e.getMessage());
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean publishSurvey(Long surveyId) {
        log.trace("Updating a survey with ID: {} in database to publish", surveyId);
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spPublishSurvey(?)");
            proc.setParameter(1, surveyId);
            proc.execute();
            return true;
        } catch (SQLException e) {
            log.error("Error while updating a survey with ID: {} in database, error: {}", surveyId, e.getMessage());
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean unpublishSurvey(Long surveyId) {
        log.trace("Updating a survey with ID: {} in database to unpublish", surveyId);
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spUnpublishSurvey(?)");
            proc.setParameter(1, surveyId);
            proc.execute();
            return true;
        } catch (SQLException e) {
            log.error("Error while updating a survey with ID: {} in database, error: {}", surveyId, e.getMessage());
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public int getSurveyStatus(Long surveyId) {
        log.trace("Fetching a status of a survey with ID: {} from database", surveyId);
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spGetSurveyStatus(?)");
            proc.setParameter(1, surveyId);
            ResultSet resultSet = proc.executeWithResults();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            log.error("Error while fetching status of a survey with ID: {} from database, error: {}", surveyId, e.getMessage());
            return -1;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public List<Question> getAllSurveyQuestions(long surveyId) {
        log.trace("Fetching all survey questions of a survey with ID: {} from database", surveyId);
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spGetAllSurveyQuestions(?)");
            proc.setParameter(1, surveyId);
            ResultSet resultSet = proc.executeWithResults();
            if (resultSet != null) {
                List<Question> list = new ArrayList<>();
                while (resultSet.next()) {
                    Question question = new Question();
                    Long id = resultSet.getLong(1);
                    String title = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    int type = resultSet.getInt(4);
                    Date createdOn = resultSet.getDate(6);
                    question.setId(id);
                    question.setTitle(title);
                    question.setDescription(description);
                    question.setQuestionType(type);
                    question.setCreatedAt(createdOn);
                    list.add(question);
                }
                return list;
            }
            return null;
        } catch (SQLException e) {
            log.error("Error while fetching all question of a survey with ID: {} from database, error: {}", surveyId, e.getMessage());
            return null;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public List<Question> getAllInstructorQuestionsUsingCourseId(long courseId, long surveyId) {
        log.trace("Fetching all instructor questions of a survey with ID: {} for a course with ID: {} from database", surveyId, courseId);
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spGetAllInstructorQuestionsUsingCourseId(?,?)");
            proc.setParameter(1, courseId);
            proc.setParameter(2, surveyId);
            ResultSet resultSet = proc.executeWithResults();
            if (resultSet != null) {
                List<Question> list = new ArrayList<>();
                while (resultSet.next()) {
                    Question question = new Question();
                    Long id = resultSet.getLong(1);
                    String title = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    int type = resultSet.getInt(4);
                    Date createdOn = resultSet.getDate(6);
                    question.setId(id);
                    question.setTitle(title);
                    question.setDescription(description);
                    question.setQuestionType(type);
                    question.setCreatedAt(createdOn);
                    list.add(question);
                }
                return list;
            }
            return null;
        } catch (SQLException e) {
            log.error("Error while fetching instructor questions of a survey with ID: {} for a course with ID: {} from " +
                    "database, error: {}", surveyId, courseId, e.getMessage());
            return null;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public List<QuestionOption> getSurveyQuestionOption(Long questionId) {
        log.trace("Fetching question options for a question with ID: {} from database", questionId);
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spGetSurveyQuestionOption(?)");
            proc.setParameter(1, questionId);
            ResultSet resultSet = proc.executeWithResults();
            if (resultSet != null) {
                List<QuestionOption> list = new ArrayList<>();
                while (resultSet.next()) {
                    QuestionOption questionOption = new QuestionOption();
                    Long id = resultSet.getLong(1);
                    Long queId = resultSet.getLong(2);
                    String options = resultSet.getString(3);
                    int value = resultSet.getInt(4);
                    questionOption.setId(id);
                    questionOption.setQuestionId(queId);
                    questionOption.setOption(options);
                    questionOption.setValue(value);
                    list.add(questionOption);
                }
                return list;
            }
            return null;
        } catch (SQLException e) {
            log.error("Error while fetching question options for a question with ID: {} from database, error: {}",
                    questionId, e.getMessage());
            return null;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public boolean submitAnswers(String bannerId, Long surveyId, Survey survey) {
        log.trace("Adding answers of a survey with ID: {} for user with banner ID: {} to database", surveyId, bannerId);
        CallStoredProcedure proc = null;
        try{
            proc = new CallStoredProcedure("spSubmitAnswers(?,?,?,?)");
            for(Question question: survey.getQuestions()){
                for(Answers answers: question.getAnswers()){
                    proc.setParameter(1,surveyId);
                    proc.setParameter(2,bannerId);
                    proc.setParameter(3,question.getId());
                    proc.setParameter(4,answers.getAnswerValue());
                    proc.addBatch();
                }
            }
            proc.executeBatch();
        }
        catch(SQLException e){
            log.error("Error while adding answers of a survey with ID: {} for user with banner ID: {} to database, error: " +
                    "{}", surveyId, bannerId, e.getMessage());
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return true;
    }
}
