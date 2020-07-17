package CSCI5308.GroupFormationTool.Courses;

import org.springframework.web.multipart.MultipartFile;

public abstract class CourseModelAbstractFactory {

    private static CourseModelAbstractFactory uniqueInstance = null;

    protected CourseModelAbstractFactory(){}

    public static CourseModelAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new CourseModelConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract ICourse makeCourse();
    public abstract IStudentCSVParser makeStudentCSVParser(MultipartFile file);
}
