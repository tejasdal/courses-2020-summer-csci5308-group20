package CSCI5308.GroupFormationTool.CoursesTest;

import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;

public interface CoursesTestAbstractFactory {

    public ICoursePersistence makeCoursePersistence();
    public ICourse makeCourse();
    public ICourseUserRelationshipPersistence makeCourseUserRelationshipPersistence();
}
