package sch.binadharma.spp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.binadharma.spp.model.entity.AcademicYear;
import sch.binadharma.spp.model.entity.Student;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, String> {
}
