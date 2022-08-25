package sch.binadharma.spp.auth;

import org.springframework.stereotype.Service;
import sch.binadharma.spp.repository.UserPermissionRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserPermissionDaoService implements UserPermissionDao {

    private Set<UserPermission> userPermission;
    private final UserPermissionRepository userPermissionRepository;

    public UserPermissionDaoService(Set<UserPermission> userPermission, UserPermissionRepository userPermissionRepository) {
        this.userPermission = userPermission;
        this.userPermissionRepository = userPermissionRepository;
    }

    @Override
    public Set<UserPermission> selectActiveUserPermission() {
        return userPermissionRepository.findAll().stream()
                .filter(
                        userPermission1 ->
                                Boolean.TRUE.equals(userPermission1.isPermissionEnabled())
                ).collect(Collectors.toSet());
    }
}
