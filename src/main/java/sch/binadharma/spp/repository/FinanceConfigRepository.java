package sch.binadharma.spp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.binadharma.spp.model.entity.FinanceConfig;

@Repository
public interface FinanceConfigRepository extends JpaRepository<FinanceConfig, String> {
}
