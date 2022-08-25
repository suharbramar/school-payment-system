package sch.binadharma.spp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.binadharma.spp.auth.ApplicationUser;

@Repository
public interface UserApplicationRepository extends JpaRepository<ApplicationUser, Long> {
}
