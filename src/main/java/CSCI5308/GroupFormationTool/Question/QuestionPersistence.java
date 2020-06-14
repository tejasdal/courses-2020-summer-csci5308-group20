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
    public boolean deleteQuestion(Long questionId) throws SQLException {
        return false;
    }

    @Override
    public List<Question> getAllQuestionsForUser(Long userId) {
        List<Question> questions = new ArrayList<Question>();
        CallStoredProcedure proc = null;
        try{
            System.out.println("aaa");
            proc = new CallStoredProcedure("spGetAllQuestionUser(?)");
            System.out.println("cc");
            System.out.println("vv");
            proc.setParameter(1,userId);
            System.out.println("hhh");
            ResultSet rs = proc.executeWithResults();
            System.out.println("nnm");
            if(rs!=null){
                while(rs.next()){
                    Question question = new Question();
                    Long id = rs.getLong(1);
                    System.out.println(id);
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
