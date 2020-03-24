package ssm.service;

import entity.Permission;
import entity.Role;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    void save(Permission permission);
}
