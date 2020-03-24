package ssm.dao;

import entity.Permission;
import entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {
    @Select("select * from role where id in(select roleid from users_role where userid=#{userid})")
    @Results({
            @Result(property = "permissions", javaType = List.class, column = "id", many = @Many(select = "ssm.dao.PermissionDao.findPermissonByRoleid"))
    })
    List<Role> findRoleByUserid(String userid);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(rolename,roledesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id=#{id}")
    Role findById(String id);

    @Select("select * from permission where id not in(select permissionid from role_permission where roleid=#{id})")
    List<Permission> findPermissionByid(String id);

    @Insert("insert into role_permission values(#{bb},#{aa})")
    void savePermissionToRole(@Param("aa") String roleId, @Param("bb") String id);
}
