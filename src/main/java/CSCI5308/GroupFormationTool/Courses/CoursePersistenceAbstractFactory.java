package CSCI5308.GroupFormationTool.Courses;

public abstract class CoursePersistenceAbstractFactory {

    private static CoursePersistenceAbstractFactory uniqueInstance = null;

    protected CoursePersistenceAbstractFactory(){}

    public static CoursePersistenceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new CoursePersistenceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract ICoursePersistence makeCoursePersistence();
    public abstract ICourseUserRelationshipPersistence makeCourseUserRelationshipPersistence();
}
