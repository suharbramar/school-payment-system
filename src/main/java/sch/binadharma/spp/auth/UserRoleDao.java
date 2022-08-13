package sch.binadharma.spp.auth;

import java.util.Set;

public interface UserRoleDao {
    Set<UserRole> selectActiveUserRole();
}
