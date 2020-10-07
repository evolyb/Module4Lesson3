package service;

import model.Class;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private static List<Student> list;
    static {
        ClassService classService = new ClassService();
        list = new ArrayList<>();
        List<Class> listOfClass = classService.getClasses();
        Student student1 = new Student(1,"Doan Hung Dung",listOfClass.get(0));
        Student student2 = new Student(2,"Nguyen Van A",listOfClass.get(0));
        list.add(student1);
        list.add(student2);
    }
    public List<Student> getStudents(){
        return list;
    }
    public Student getStudentById(int id){
        for (Student student: list){
            if (student.getId() == id) return student;
        }
        return null;
    }
    public void saveStudent(Student student){
        list.add(student);
    }
    public void saveChanges(Student student){
        Student student1 = getStudentById(student.getId());
        student1.setName(student.getName());
        student1.setClazz(student.getClazz());
    }
    public void deleteStudent(int id){
        Student student1 = getStudentById(id);
        list.remove(student1);
    }
}
