package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionPersistence implements IQuestionPersistence {

    @Override
    public boolean createQuestion(IQuestion question) throws SQLException {
        CallStoredProcedure proc = null;
        try{
            proc = new CallStoredProcedure("spCreateQuestion(?,?,?,?,?,?)");
            proc.setParameter(1, question.getId());
            proc.setParameter(2, question.getTitle());
            proc.setParameter(3, question.getDescription());
            proc.setParameter(4, question.getUserId());
            proc.setParameter(5, question.getQuestionType());
            proc.setParameter(6, question.getCreatedAt());
            proc.execute();
            if (question.getQuestionOptions() != null && !question.getQuestionOptions().isEmpty()) {
                createQuestionOptions(question.getQuestionOptions(), question.getId());
            }
            return true;
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    private static void createQuestionOptions(List<QuestionOption> questionOptions, Long questionId) throws SQLException {
        CallStoredProcedure proc = null;
        try{
            proc = new CallStoredProcedure("spCreateQuestionOption( ?, ?, ?)");
            for (IQuestionOption questionOption : questionOptions) {
                proc.setParameter(1, questionId);
                proc.setParameter(2, questionOption.getOption());
                proc.setParameter(3, questionOption.getValue());
                proc.addBatch();
            }
            proc.executeBatch();
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public boolean deleteQuestion(Long questionId) {
        CallStoredProcedure proc = null;
        try{
            proc = new CallStoredProcedure("spDeleteQuestion(?)");
            proc.setParameter(1,questionId);
            proc.execute();
        }
        catch(SQLException e){
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

    @Override
    public List<IQuestion> getAllUserQuestions(Long userId) {
        List<IQuestion> questions = new ArrayList<IQuestion>();
        CallStoredProcedure proc = null;
        try{
            proc = new CallStoredProcedure("spGetAllQuestionUser(?)");
            proc.setParameter(1,userId);
            ResultSet rs = proc.executeWithResults();
            if(rs!=null){
                while(rs.next()){
                    IQuestion question = QuestionServiceAbstractFactory.instance().makeQuestion();
                    Long id = rs.getLong(1);
                    String title = rs.getString(2);
                    String description = rs.getString(3);
                    int type = rs.getInt(4);
                    Date createdOn = rs.getDate(6);
                    question.setId(id);
                    question.setTitle(title);
                    question.setDescription(description);
                    question.setQuestionType(type);
                    question.setCreatedAt(createdOn);
                    questions.add(question);
                }
            }

        }
        catch(SQLException e){
            //Logging
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return questions;
    }
}
