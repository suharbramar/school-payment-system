package sch.binadharma.spp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.binadharma.spp.model.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByIsDeletedFalse();
    Optional<Student> findByStudentNisAndIsDeletedFalse(Long id);

    boolean existsById(Long id);
}
