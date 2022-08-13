package sch.binadharma.spp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.binadharma.spp.auth.UserPermission;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, String> {
}
