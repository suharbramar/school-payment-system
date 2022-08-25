package sch.binadharma.spp.security;

//import io.bramcode.movie.moviecategoryservices.auth.UserPermission;
//import io.bramcode.movie.moviecategoryservices.auth.UserPermissionDaoService;
//import io.bramcode.movie.moviecategoryservices.auth.UserRoleDaoService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import sch.binadharma.spp.auth.UserPermission;
import sch.binadharma.spp.auth.UserPermissionDaoService;
import sch.binadharma.spp.auth.UserRoleDaoService;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ApplicationUserRole {

    private final UserPermissionDaoService userPermissionDaoService;
    private final UserRoleDaoService userRoleDaoService;

    public ApplicationUserRole(UserPermissionDaoService userPermissionDaoService, UserRoleDaoService userRoleDaoService) {
        this.userPermissionDaoService = userPermissionDaoService;
        this.userRoleDaoService = userRoleDaoService;
    }

    public Set<UserPermission> getPermissions() {
        return userPermissionDaoService.selectActiveUserPermission();
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(String roleName) {
        Boolean isRoleNameActive = userRoleDaoService.selectActiveUserRole().stream()
                .filter(userRole -> userRole.getRoleName().equalsIgnoreCase(roleName)).findFirst().isPresent();

        if(isRoleNameActive){
            Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                    .filter(userPermission -> userPermission.getUserRole().getRoleName().equalsIgnoreCase(roleName))
                    .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
                    .collect(Collectors.toSet());
            permissions.add(new SimpleGrantedAuthority("ROLE_" + roleName));
            return permissions;
        }

        return null;

    }
}
