package sch.binadharma.spp.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sch.binadharma.spp.model.entity.Student;
import sch.binadharma.spp.service.StudentService;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    private final String ADD_EDIT_STUDENT = "/student/add-edit-student";
    private final String LIST_REDIRECT = "redirect:/student/list";

    private final static  List<ProvinceConfig> provinceConfigList = Arrays.asList(new ProvinceConfig("JKT", "DKI JAKARTA"),
            new ProvinceConfig("JABAR", "JAWA BARAT"));
    private final static List<SchoolNameConfig> schoolNameConfigList = Arrays.asList(new SchoolNameConfig("SMPBD3BDG", "SMP BINADHARMA 3 BANDUNG"),
            new SchoolNameConfig("SMABD1BDG", "SMA BINADHARMA 1 BANDUNG"));


    @GetMapping("/list")
    public String retreiveAllStudent(Model model) {
        model.addAttribute("listStudents", studentService.retreiveAllStudent());
        return "/student/list-student";
    }

    @GetMapping("/add")
    public String addStudent(Student student, Model model) {
        student.setAcademicId("2021-2022");
        model.addAttribute("provinceConfigs", provinceConfigList);
        model.addAttribute("schoolNameConfigs", schoolNameConfigList);
        model.addAttribute("student", student);
        return ADD_EDIT_STUDENT;
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable(value = "id") String studentNis, Model model) {
        Student studentToUpdate = studentService.retreiveByStudentNis(studentNis);
        model.addAttribute("provinceConfigs", provinceConfigList);
        model.addAttribute("schoolNameConfigs", schoolNameConfigList);
        model.addAttribute("student", studentToUpdate);
        return ADD_EDIT_STUDENT;
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") String studentNisn, Model model) {
        studentService.deleteStudent(studentNisn);
        return LIST_REDIRECT;
    }

    @PostMapping("/save")
    public String createCategory(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model
    ) {
        model.addAttribute("student", student);
        model.addAttribute("provinceConfigs", provinceConfigList);
        model.addAttribute("schoolNameConfigs", schoolNameConfigList);
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return ADD_EDIT_STUDENT;
        }
        studentService.saveStudent(student);

        return LIST_REDIRECT;
    }

    @Data
    @AllArgsConstructor
    public static class ProvinceConfig {
        private String provinceId;
        private String provinceName;
    }

    @Data
    @AllArgsConstructor
    public static class SchoolNameConfig {
        private String schoolId;
        private String schoolName;
    }


    //    @GetMapping("/{id}")
//    public ResponseEntity<Object> getStudentById(@PathVariable(value = "id") Long studentId) throws NotFoundException {
//        Student student = studentService.retreiveByStudentNis(studentId);
//        if(Objects.nonNull(student)){
//            return new ResponseEntity<>(student, HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
//    }
//


}
