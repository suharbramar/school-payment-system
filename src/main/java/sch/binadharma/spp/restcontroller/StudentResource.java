package sch.binadharma.spp.restcontroller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sch.binadharma.spp.model.entity.Student;
import sch.binadharma.spp.service.StudentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/student")
public class StudentResource {

    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public List<Student> retreiveAllStudent() {
        return studentService.retreiveAllStudent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable(value = "id") String studentId) throws NotFoundException {
        Student student = studentService.retreiveByStudentNis(studentId);
        if(Objects.nonNull(student)){
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public Student createCategory(@Valid @RequestBody Student student
    ) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable(value = "id") String studentId,
                                 @Valid @RequestBody Student studentDetails) {

        return studentService.updateStudent(studentId, studentDetails);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") String studentId) {
        return studentService.deleteStudent(studentId);
    }


}
