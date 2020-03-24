package ssm.service;

import entity.Permission;
import entity.Role;


import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void save(Role role);

    Role findById(String id);

    List<Permission> findPermissionByid(String id);

    void savePermissionToRole(String roleId, String[] permissionid);
}
