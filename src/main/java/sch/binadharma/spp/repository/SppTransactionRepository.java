package sch.binadharma.spp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.binadharma.spp.model.entity.SppTransaction;

import java.util.UUID;

@Repository
public interface SppTransactionRepository extends JpaRepository<SppTransaction, UUID> {
}
