package CSCI5308.GroupFormationTool.Courses;

import org.springframework.web.multipart.MultipartFile;

public class CourseModelConcreteFactory extends CourseModelAbstractFactory {
    @Override
    public ICourse makeCourse() {
        return new Course();
    }

    @Override
    public IStudentCSVParser makeStudentCSVParser(MultipartFile file) {
        return new StudentCSVParser(file);
    }
}
