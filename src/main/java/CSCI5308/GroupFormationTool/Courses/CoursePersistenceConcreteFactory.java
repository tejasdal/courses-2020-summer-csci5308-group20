package CSCI5308.GroupFormationTool.Courses;

public class CoursePersistenceConcreteFactory extends CoursePersistenceAbstractFactory {
    @Override
    public ICoursePersistence makeCoursePersistence() {
        return new CourseDB();
    }

    @Override
    public ICourseUserRelationshipPersistence makeCourseUserRelationshipPersistence() {
        return new CourseUserRelationshipDB();
    }
}
