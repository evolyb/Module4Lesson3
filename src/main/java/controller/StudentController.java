package controller;

import model.Class;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.ClassService;
import service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    ClassService classService;

    @Autowired
    StudentService studentService;

    @GetMapping("")
    public String showStudents(Model model){
        List<Student> list = studentService.getStudents();
        model.addAttribute("list",list);
        return "list";
    }

    @GetMapping("/{id}")
    public String showStudents(@PathVariable int id, Model model){
        Student student = studentService.getStudentById(id);
        if (student == null) student = new Student();
        model.addAttribute("student",student);
        return "detail";
    }
    @GetMapping("/create")
    public String showAddForm(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        List<Class> classes = classService.getClasses();
        model.addAttribute("classes",classes);
        return "create";
    }
    @PostMapping("/create")
    public String showAddForm(@ModelAttribute("student") Student student, int clazz_id, Model model){
        student.setClazz(classService.getClassById(clazz_id));
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    @GetMapping("/edit/{id}")
    public String showAddForm(@PathVariable int id, Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        List<Class> classes = classService.getClasses();
        model.addAttribute("classes",classes);
        return "edit";
    }
    @PostMapping("/edit")
    public String saveStudent( @ModelAttribute("student") Student student, int clazz_id, Model model){
        student.setClazz(classService.getClassById(clazz_id));
        studentService.saveChanges(student);
        return "redirect:/students";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id, Model model){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
