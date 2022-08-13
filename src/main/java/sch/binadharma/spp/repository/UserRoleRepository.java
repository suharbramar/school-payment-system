package sch.binadharma.spp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.binadharma.spp.auth.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
