package vn.com.ecotechgroup.erp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.com.ecotechgroup.erp.entity.Role;
import vn.com.ecotechgroup.erp.repository.RoleRepository;
import vn.com.ecotechgroup.erp.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getOne(Long l) {
        return roleRepository.findById(l).orElse(null);
    }

    @Override
    public Page<Role> getListPage(Pageable pageable, String searchTerm) {
        return roleRepository.roleSearchList(pageable, searchTerm);
    }
}
