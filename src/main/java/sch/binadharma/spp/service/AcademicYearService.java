package sch.binadharma.spp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sch.binadharma.spp.model.entity.AcademicYear;
import sch.binadharma.spp.repository.AcademicYearRepository;

import java.util.*;

@Service
@Slf4j
public class AcademicYearService {

    //private static final Logger logger = (Logger) LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private AcademicYearRepository academicYearRepository;

    public AcademicYearService() {
    }

    public List<AcademicYear> retreiveAllAcademicYear() {
        return academicYearRepository.findAll();
    }

    public AcademicYear retreiveByAcademicYearId(String academicYearId) {
        //logger.info("Inside category response..");

        Optional<AcademicYear> optionalAcademicYear = academicYearRepository.findById(academicYearId);

        if (optionalAcademicYear.isPresent()) {
            AcademicYear responseAcademicYear = optionalAcademicYear.get();

            return responseAcademicYear;
        }

        return null;

    }

    public AcademicYear saveAcademicYear(AcademicYear academicYear) {
        return academicYearRepository.save(academicYear);
    }

    public AcademicYear updateAcademicYear(String academicYearId, AcademicYear academicYear) {
        Optional<AcademicYear> optAcademicYear = academicYearRepository.findById(academicYearId);

        if (optAcademicYear.isPresent()) {
            AcademicYear updAcademicYear = optAcademicYear.get();
            updAcademicYear.setAcademicName(academicYear.getAcademicName());
            updAcademicYear.setAcademicStartDate(academicYear.getAcademicStartDate());
            updAcademicYear.setAcademicEndDate(academicYear.getAcademicEndDate());
            updAcademicYear.setAcademicNote(academicYear.getAcademicNote());
            updAcademicYear.setIsDeleted(academicYear.getIsDeleted());
            updAcademicYear.setUpdateBy("ADMIN");

            academicYearRepository.save(updAcademicYear);

            return updAcademicYear;

        } else {
            ResponseEntity.notFound();
        }

        return null;
    }

    public Map<String, Boolean> deleteAcademicYear(String academicYearId) {
        AcademicYear deleteAcademicYear = retreiveByAcademicYearId(academicYearId);

        Map<String, Boolean> response = new HashMap<>();
        if (Objects.nonNull(deleteAcademicYear)) {
            academicYearRepository.delete(deleteAcademicYear);
            response.put("Deleted", Boolean.TRUE);
        } else {
            ResponseEntity.notFound();
        }

        return response;
    }

}
