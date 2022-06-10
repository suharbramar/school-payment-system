package sch.binadharma.spp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sch.binadharma.spp.model.entity.AcademicYear;
import sch.binadharma.spp.model.entity.Spp;
import sch.binadharma.spp.model.entity.Student;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SppRepository extends JpaRepository<Spp, UUID> {
    @Query(value = "SELECT COUNT(spp_id) as spp_id FROM SPP WHERE " +
            "student_nis = :studentNis " +
            "AND academic_id = :academicId ", nativeQuery = true)
    Integer countExistingStudentNisAndAcademicYear(long studentNis, String academicId);

    Optional<Spp> findBySppNameAndAcademicYearAndStudentIdAndIsDeleted(String sppName, AcademicYear academicYear, Student student, Boolean isDeleted);
}
