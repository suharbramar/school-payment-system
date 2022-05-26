package sch.binadharma.spp.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sch.binadharma.spp.model.entity.Student;
import sch.binadharma.spp.service.StudentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public List<Student> retreiveAllStudent() {
        return studentService.retreiveAllStudent();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable(value = "id") Long studentId) throws NotFoundException {
        return studentService.retreiveByStudentNis(studentId);
    }

    @PostMapping("/save")
    public Student createCategory(@Valid @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable(value = "id") long studentId,
                                 @Valid @RequestBody Student studentDetails) {

        return studentService.updateStudent(studentId, studentDetails);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") long studentId) {
        return studentService.deleteStudent(studentId);
    }


}
