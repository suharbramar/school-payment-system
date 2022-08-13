package sch.binadharma.spp.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "ApplicationUser")
@Table(name = "applicationuser")
public class ApplicationUser implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "applicationuser_sequence",
            sequenceName = "application_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "applicationuser_sequence"
    )
    @Column(
            name = "applicationuser_id",
            updatable = false
    )
    private Long applicationUserId;

    @Transient
    private Set<SimpleGrantedAuthority> grantedAuthorityList = new HashSet<>();

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_name", length = 50, nullable = false)
    private String username;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_accountnonexpired", nullable = false)
    private boolean isAccountNonExpired;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_accountnonlocked", nullable = false)
    private boolean isAccountNonLocked;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_credentialsnonexpired", nullable = false)
    private boolean isCredentialsNonExpired;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")
    private UserRole userRole;



    public ApplicationUser() {
    }

    public ApplicationUser(
            String username,
            String password,
            Set<SimpleGrantedAuthority> grantedAuthorityList,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired,
            boolean isEnabled) {
        this.grantedAuthorityList = grantedAuthorityList;
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(Long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorityList() {
        return grantedAuthorityList;
    }

    public void setGrantedAuthorityList(Set<SimpleGrantedAuthority> grantedAuthorityList) {
        this.grantedAuthorityList = grantedAuthorityList;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }


}
