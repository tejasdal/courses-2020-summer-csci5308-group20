package CSCI5308.GroupFormationTool.SurveyManagement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionOption;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionOption;

public class SurveyPersistence implements ISurveyPersistence {

    public long getSurveyIdUsingCourseId(long courseId) {
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
            return (-1);
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean createSurvey(long courseId) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spCreateSurvey(?)");
            proc.setParameter(1, courseId);
            proc.execute();
            return true;
        } catch (SQLException e) {
            return false;
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean addQuestionToSurvey(long surveyId, long questionId) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spAddQuestionToSurvey(?,?)");
            proc.setParameter(1, surveyId);
            proc.setParameter(2, questionId);
            proc.execute();
            return true;
        } catch (SQLException e) {
            return false;
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean deleteQuestionFromSurvey(Long surveyId, Long questionId) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spDeleteQuestionFromSurvey(?,?)");
            proc.setParameter(1, surveyId);
            proc.setParameter(2, questionId);
            proc.execute();
            return true;
        } catch (SQLException e) {
            // Logging needed.
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean publishSurvey(Long surveyId) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spPublishSurvey(?)");
            proc.setParameter(1, surveyId);
            proc.execute();
            return true;
        } catch (SQLException e) {
            // Logging needed.
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean unpublishSurvey(Long surveyId) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spUnpublishSurvey(?)");
            proc.setParameter(1, surveyId);
            proc.execute();
            return true;
        } catch (SQLException e) {
            // Logging needed.
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public int getSurveyStatus(Long surveyId) {
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
            // Logging needed.
            return -1;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public List<IQuestion> getAllSurveyQuestions(long surveyId) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spGetAllSurveyQuestions(?)");
            proc.setParameter(1, surveyId);
            ResultSet resultSet = proc.executeWithResults();
            if (resultSet != null) {
                List<IQuestion> list = new ArrayList<>();
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
                    question.setQuestionTypeString(question.getQuestionTypeStringMapping(type));
                    list.add(question);
                }
                return list;
            }
            return null;
        } catch (SQLException e) {
            return null;
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public List<IQuestion> getAllInstructorQuestionsUsingCourseId(long courseId, long surveyId) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spGetAllInstructorQuestionsUsingCourseId(?,?)");
            proc.setParameter(1, courseId);
            proc.setParameter(2, surveyId);
            ResultSet resultSet = proc.executeWithResults();
            if (resultSet != null) {
                List<IQuestion> list = new ArrayList<>();
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
            return null;
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public List<IQuestionOption> getSurveyQuestionOption(Long questionId) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spGetSurveyQuestionOption(?)");
            proc.setParameter(1, questionId);
            ResultSet resultSet = proc.executeWithResults();
            if (resultSet != null) {
                List<IQuestionOption> list = new ArrayList<>();
                while (resultSet.next()) {
                    IQuestionOption questionOption = new QuestionOption();
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
            return null;
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public boolean submitAnswers(String bannerId, Long surveyId, Survey survey) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spSubmitAnswers(?,?,?,?,?)");
            for (IQuestion question : survey.getQuestions()) {
                for (UserAnswer answer : survey.getUserAnswers().get(question.getId()).get(bannerId)) {
                    proc.setParameter(1, surveyId);
                    proc.setParameter(2, bannerId);
                    proc.setParameter(3, question.getId());
                    proc.setParameter(4, answer.getAnswerRaw());
                    proc.setParameter(5, answer.getAnswerIndex());
                    proc.addBatch();
                }
            }
            proc.executeBatch();
        } catch (SQLException e) {
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return true;
    }

	@Override
	public List<User> getAllParticipants(Long surveyId) {
		CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spGetParticipantsOfSurvey(?)");
            proc.setParameter(1, surveyId);
            ResultSet results = proc.executeWithResults();
            if (results != null) {
                List<User> list = new ArrayList<>();
                while (results.next()) {
                	long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String password = results.getString(3);
					String firstName = results.getString(4);
					String lastName = results.getString(5);
					String email = results.getString(6);
					User user = new User();
					user.setID(userID);
					user.setBannerID(bannerID);
					user.setPassword(password);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setEmail(email);
                    list.add(user);
                }
                return list;
            }
            return null;
        } catch (SQLException e) {
            return null;
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
	}
	
	@Override
	public ISurveyResponse getSurveyResponses(Long surveyId) {
		CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spGetAllAnswerBySurveyId(?)");
            proc.setParameter(1, surveyId);
            ResultSet results = proc.executeWithResults();
            if (results != null) {
                ISurveyResponse responses = SurveyFactory.instance().createSurveyResponse();
                while (results.next()) {
                	Long userId = results.getLong(2);
					Long questionId = results.getLong(1);
					String answerRaw = results.getString(3);
					Integer answerIndex = results.getInt(4);
					UserAnswer answer = new UserAnswer(answerRaw, answerIndex);
					responses.setUserAnswer(questionId, userId, answer);
                }
                return responses;
            }
            return null;
        } catch (SQLException e) {
            return null;
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
	}
}
