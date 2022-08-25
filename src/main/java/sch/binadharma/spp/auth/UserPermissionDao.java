package sch.binadharma.spp.auth;

import java.util.Set;

public interface UserPermissionDao {
    Set<UserPermission> selectActiveUserPermission();
}
