package sch.binadharma.spp.restcontroller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sch.binadharma.spp.model.entity.AcademicYear;
import sch.binadharma.spp.service.AcademicYearService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/academicyear/v1")
public class AcademicYearResource {

    @Autowired
    AcademicYearService academicYearService;

    @GetMapping("/all")
    public List<AcademicYear> retreiveAllAcademicYear() {
        return academicYearService.retreiveAllAcademicYear();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAcademicYearById(@PathVariable(value = "id") String academicYearId) throws NotFoundException {
        AcademicYear academicYear = academicYearService.retreiveByAcademicYearId(academicYearId);
        if (Objects.nonNull(academicYear)) {
            return new ResponseEntity<>(academicYear, HttpStatus.OK);
        }
        return new ResponseEntity<>("Academic Year not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AcademicYear createAcademicYear(@Valid @RequestBody AcademicYear academicYear) {
        return academicYearService.saveAcademicYear(academicYear);
    }

    @PutMapping("/update/{id}")
    public AcademicYear updateAcademicYear(@PathVariable(value = "id") String academicYearId,
                                           @Valid @RequestBody AcademicYear academicYear) {
        return academicYearService.updateAcademicYear(academicYearId, academicYear);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteAcademicYear(@PathVariable(value = "id") String academicYearId) {
        return academicYearService.deleteAcademicYear(academicYearId);
    }
}
