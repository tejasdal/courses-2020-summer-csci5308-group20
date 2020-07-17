package CSCI5308.GroupFormationTool.CoursesTest;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;

public class CoursesTestConcreteFactory implements CoursesTestAbstractFactory {
    @Override
    public ICoursePersistence makeCoursePersistence() {
        return new CourseDBMock();
    }

    @Override
    public ICourse makeCourse() {
        return new Course();
    }

    @Override
    public ICourseUserRelationshipPersistence makeCourseUserRelationshipPersistence() {
        return new CourseUserRelationshipDBMock();
    }

}
