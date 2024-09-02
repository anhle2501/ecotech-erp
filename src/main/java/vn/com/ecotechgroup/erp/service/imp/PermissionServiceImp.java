package vn.com.ecotechgroup.erp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.com.ecotechgroup.erp.entity.Permission;
import vn.com.ecotechgroup.erp.entity.Role;
import vn.com.ecotechgroup.erp.repository.PermissionRepository;
import vn.com.ecotechgroup.erp.repository.RoleRepository;
import vn.com.ecotechgroup.erp.service.PermissionService;
import vn.com.ecotechgroup.erp.service.RoleService;

@Service
public class PermissionServiceImp implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission update(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission getOne(Long l) {
        return permissionRepository.findById(l).orElse(null);
    }

    @Override
    public Page<Permission> getListPage(Pageable pageable, String searchTerm) {
        return permissionRepository.permissionSearchList(pageable, searchTerm);
    }
}
