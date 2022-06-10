package sch.binadharma.spp.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static io.bramcode.movie.moviecategoryservices.security.ApplicationUserPermission.*;


public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(ADMIN_CATEGORY_READ,ADMIN_CATEGORY_DELETE)),
    ADMIN_TRAINEE(Sets.newHashSet(ADMIN_CATEGORY_READ, ADMIN_CATEGORY_WRITE)),
    STAFF(Sets.newHashSet(STAFF_CATEGORY_READ, STAFF_CATEGORY_WRITE)); // set empty for permission

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    //Load enum data and populate into Set<>
    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
