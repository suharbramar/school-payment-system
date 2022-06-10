package sch.binadharma.spp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sch.binadharma.spp.model.entity.FinanceConfig;

import java.util.List;

@Repository
public interface FinanceConfigRepository extends JpaRepository<FinanceConfig, String> {
    @Query(value = "SELECT * from FINANCECONFIG fc WHERE " +
            "FINANCE_TYPE = :financeType " +
            "AND academic_id = :academicId "
            , nativeQuery = true)
    List<FinanceConfig> findByFinanceTypeAndAcademicId(String financeType, String academicId);

}
