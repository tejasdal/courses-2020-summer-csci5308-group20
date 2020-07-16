package CSCI5308.GroupFormationTool.Question;

public abstract class ServiceAbstractFactory {

    private static ServiceAbstractFactory uniqueInstance = null;

    protected ServiceAbstractFactory(){}

    public static ServiceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new ServiceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IQuestionService makeService();
    public abstract IQuestion makeQuestion();
    public abstract IQuestionOption makeQuestionOption();
}
