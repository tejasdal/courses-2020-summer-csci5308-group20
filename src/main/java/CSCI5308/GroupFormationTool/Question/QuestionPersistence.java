package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionPersistence implements IQuestionPersistence {

    @Override
    public boolean addQuestion(Question question) throws SQLException {
        return false;
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
    public List<Question> getAllQuestionsForUser(Long userId,String sortBy) {
        List<Question> questions = new ArrayList<Question>();
        CallStoredProcedure proc = null;
        try{
            proc = new CallStoredProcedure("spGetAllQuestionUser(?,?)");
            proc.setParameter(1,userId);
            proc.setParameter(2,sortBy);
            ResultSet rs = proc.executeWithResults();
            if(rs!=null){
                while(rs.next()){
                    Question question = new Question();
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
            System.out.println(e.getMessage());
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
