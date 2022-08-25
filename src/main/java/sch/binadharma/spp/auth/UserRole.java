package sch.binadharma.spp.auth;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "UserRole")
@Table(name = "userrole")
public class UserRole implements Serializable {

    @Id
    @SequenceGenerator(
            name = "userrole_sequence",
            sequenceName = "userrole_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userrole_sequence"
    )
    @Column(
            name = "role_id",
            updatable = false
    )
    private Long roleId;

    @OneToMany(targetEntity = ApplicationUser.class, mappedBy = "userRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ApplicationUser> applicationUsers = new ArrayList<>();

    @NaturalId
    @Column(name = "role_name", length = 50, nullable = false)
    private String roleName;

    @Column(name = "role_description", length = 100, nullable = true)
    private String roleDescription;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "isRole_Enabled", nullable = false)
    private boolean isRoleEnabled;

    public UserRole() {
    }

    public UserRole(Long roleId, List<ApplicationUser> applicationUsers, String roleName, String roleDescription, boolean isRoleEnabled) {
        this.roleId = roleId;
        this.applicationUsers = applicationUsers;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.isRoleEnabled = isRoleEnabled;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<ApplicationUser> getApplicationUsers() {
        return applicationUsers;
    }

    public void setApplicationUsers(List<ApplicationUser> applicationUsers) {
        this.applicationUsers = applicationUsers;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public boolean isRoleEnabled() {
        return isRoleEnabled;
    }

    public void setRoleEnabled(boolean roleEnabled) {
        isRoleEnabled = roleEnabled;
    }
}
