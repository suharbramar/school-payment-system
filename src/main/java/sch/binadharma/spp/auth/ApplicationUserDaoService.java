package sch.binadharma.spp.auth;

//import io.bramcode.movie.moviecategoryservices.repository.UserApplicationRepository;
//import io.bramcode.movie.moviecategoryservices.security.ApplicationUserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sch.binadharma.spp.auth.ApplicationUser;
import sch.binadharma.spp.repository.UserApplicationRepository;
import sch.binadharma.spp.security.ApplicationUserRole;

import java.util.List;
import java.util.Optional;
import java.util.Set;

//import static io.bramcode.movie.moviecategoryservices.security.ApplicationUserRole.ADMIN;

//@Repository("RepoUserDao")//this means to tell spring that this class need to be initiate
@Service
public class ApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;
    private final UserApplicationRepository userApplicationRepository;
    private final ApplicationUserRole applicationUserRole;

    public ApplicationUserDaoService(PasswordEncoder passwordEncoder, UserApplicationRepository userApplicationRepository,
                                     ApplicationUserRole applicationUserRole) {
        this.passwordEncoder = passwordEncoder;
        this.userApplicationRepository = userApplicationRepository;
        this.applicationUserRole = applicationUserRole;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {

        Optional<ApplicationUser> applicationUser = getApplicationUser().stream()
                .filter(user -> user.getUsername().equals(username)).findFirst();

        if (applicationUser.isPresent()) {
            String roleName = applicationUser.get().getUserRole().getRoleName();
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = applicationUserRole.getGrantedAuthorities(roleName);
            applicationUser.get().setGrantedAuthorityList(simpleGrantedAuthorities);
        }

        return applicationUser;
    }

    private List<ApplicationUser> getApplicationUser() {
        List<ApplicationUser> applicationUsers = userApplicationRepository.findAll();
        return applicationUsers;
    }
}
