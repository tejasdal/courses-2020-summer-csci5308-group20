package CSCI5308.GroupFormationTool.Question;

public abstract class QuestionServiceAbstractFactory {

    private static QuestionServiceAbstractFactory uniqueInstance = null;

    protected QuestionServiceAbstractFactory(){}

    public static QuestionServiceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new QuestionServiceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IQuestionService makeService();
    public abstract IQuestion makeQuestion();
    public abstract IQuestionOption makeQuestionOption();
}
