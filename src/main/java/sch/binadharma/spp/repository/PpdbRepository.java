package sch.binadharma.spp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sch.binadharma.spp.model.entity.Ppdb;
import sch.binadharma.spp.model.entity.Spp;

import java.util.UUID;

@Repository
public interface PpdbRepository extends JpaRepository<Ppdb, UUID> {
}
