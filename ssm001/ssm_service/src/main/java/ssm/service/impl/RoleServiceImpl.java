package ssm.service.impl;

import entity.Permission;
import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.dao.RoleDao;
import ssm.service.RoleService;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findPermissionByid(String id) {
        return roleDao.findPermissionByid(id);
    }

    @Override
    public void savePermissionToRole(String roleId, String[] permissionid) {
        for (String id : permissionid) {
            roleDao.savePermissionToRole(roleId, id);
        }
    }
}
