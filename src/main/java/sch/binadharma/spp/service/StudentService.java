package sch.binadharma.spp.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.binadharma.spp.exception.CustomException;
import sch.binadharma.spp.model.entity.Spp;
import sch.binadharma.spp.model.entity.Student;
import sch.binadharma.spp.repository.StudentRepository;

import java.util.*;

@Service
@Slf4j
@Transactional
public class StudentService {

    //private static final Logger logger = (Logger) LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SppService sppService;

    public StudentService() {
    }

    public List<Student> retreiveAllStudent() {
        return studentRepository.findAllByIsDeletedFalse();
    }

    public Student retreiveByStudentNis(String studentNis) {
        //logger.info("Inside category response..");

        Optional<Student> optionalStudent = studentRepository.findById(studentNis);

        if (optionalStudent.isPresent()) {
            Student responseStudent = optionalStudent.get();

            return responseStudent;
        }

        return null;

    }

    public Student saveStudent(Student student) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedName = authentication.getName();
        student.setIsDeleted(Boolean.FALSE);
        student.setCreateBy(loggedName);
        student.setUpdateBy(loggedName);
        return studentRepository.save(student);
    }

    public Student updateStudent(String studentId, Student student) {
        Optional<Student> optStudent = studentRepository.findById(studentId);

        if (optStudent.isPresent()) {
            Student updStudent = optStudent.get();
            updStudent.setStudentDateOfBirth(student.getStudentDateOfBirth());
            updStudent.setStudentSchoolName(student.getStudentSchoolName());
            updStudent.setStudentAddress(student.getStudentAddress());
            updStudent.setStudentPhoneNumber(student.getStudentPhoneNumber());
            updStudent.setIsDeleted(student.getIsDeleted());
            updStudent.setUpdateBy("ADMIN"); //need get informastion from login

            studentRepository.save(updStudent);

            return updStudent;

        } else {
            ResponseEntity.notFound();
        }

        return null;
    }

    public Map<String, Boolean> deleteStudent(String studentId) {
        Optional<Student> studentToDelete = studentRepository.findByStudentNisnAndIsDeletedFalse(studentId);

        Map<String, Boolean> response = new HashMap<>();
        studentToDelete.ifPresentOrElse(
                student -> {
                    student.setIsDeleted(Boolean.TRUE);
                    studentRepository.save(student);

                    response.put("Deleted", Boolean.TRUE);
                }, () -> new CustomException("Data not found", "Student ID not found", "Please check Student ID "+studentId)
        );

        return response;


//        if (Objects.nonNull(studentToDelete)) {
//
//            //studentRepository.delete(deleteStudent);
//            //response.put("Deleted", Boolean.TRUE);
//        } else {
//            ResponseEntity.notFound();
//        }
//
////        Student deleteStudent = retreiveByStudentNis(studentId);
////
////        Map<String, Boolean> response = new HashMap<>();
////        if (Objects.nonNull(deleteStudent)) {
////            studentRepository.delete(deleteStudent);
////            response.put("Deleted", Boolean.TRUE);
////        } else {
////            ResponseEntity.notFound();
////        }
    }

}
