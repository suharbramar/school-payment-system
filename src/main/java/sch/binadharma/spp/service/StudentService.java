package sch.binadharma.spp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sch.binadharma.spp.model.entity.Student;
import sch.binadharma.spp.repository.StudentRepository;

import java.util.*;

@Service
@Slf4j
public class StudentService {

    //private static final Logger logger = (Logger) LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public StudentService() {
    }

    public List<Student> retreiveAllStudent() {
        return studentRepository.findAll();
    }

    public Student retreiveByStudentNis(Long studentNis) {
        //logger.info("Inside category response..");

        Optional<Student> optionalStudent = studentRepository.findById(studentNis);

        if (optionalStudent.isEmpty()) {
            ResponseEntity.notFound();
        }

        Student student = optionalStudent.get();
        Student respStudent = Student.builder()
                .studentNis(student.getStudentNis())
                .studentName(student.getStudentName())
                .studentClass(student.getStudentClass())
                .studentDateOfBirth(student.getStudentDateOfBirth())
                .studentOriginatorSchoolName(student.getStudentOriginatorSchoolName())
                .studentSchoolName(student.getStudentSchoolName())
                .studentAddress(student.getStudentAddress())
                .studentPhoneNumber(student.getStudentPhoneNumber())
                .studentStatus(student.getStudentStatus())
                .build();

        return respStudent;

    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long studentId, Student student) {
        Optional<Student> optStudent = studentRepository.findById(studentId);

        if (optStudent.isPresent()) {
            Student updStudent = optStudent.get();
            updStudent.setStudentName(student.getStudentName());
            updStudent.setStudentClass(student.getStudentClass());
            updStudent.setStudentDateOfBirth(student.getStudentDateOfBirth());
            updStudent.setStudentOriginatorSchoolName(student.getStudentOriginatorSchoolName());
            updStudent.setStudentSchoolName(student.getStudentSchoolName());
            updStudent.setStudentAddress(student.getStudentAddress());
            updStudent.setStudentPhoneNumber(student.getStudentPhoneNumber());
            updStudent.setStudentStatus(student.getStudentStatus());

            studentRepository.save(updStudent);

            return updStudent;

        } else {
            ResponseEntity.notFound();
        }

        return null;
    }

    public Map<String, Boolean> deleteStudent(Long studentId) {
        Student deleteStudent = retreiveByStudentNis(studentId);

        Map<String, Boolean> response = new HashMap<>();
        if (Objects.nonNull(deleteStudent)) {
            studentRepository.delete(deleteStudent);
            response.put("Deleted", Boolean.TRUE);
        } else {
            ResponseEntity.notFound();
        }

        return response;
    }

}
