package sch.binadharma.spp.auth;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name = "UserPermission")
@Table(name = "userpermission")
public class UserPermission {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "permission_id",
            updatable = false
    )
    private Long permissionId;

    @Column(name = "permission_name", length = 100, nullable = false)
    private String permissionName;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "isPermission_Enabled", nullable = false)
    private boolean isPermissionEnabled;

    @ManyToOne
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")
    private UserRole userRole;

    public UserRole getUserRole() {
        return userRole;
    }

    public UserPermission() {
    }

    public UserPermission(Long permissionId, String permissionName, boolean isPermissionEnabled, UserRole userRole) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.isPermissionEnabled = isPermissionEnabled;
        this.userRole = userRole;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public boolean isPermissionEnabled() {
        return isPermissionEnabled;
    }

    public void setPermissionEnabled(boolean permissionEnabled) {
        isPermissionEnabled = permissionEnabled;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
