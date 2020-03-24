package entity;

import org.springframework.security.core.userdetails.User;

import java.util.List;

public class Role {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }


    private String id;
    private String roleName;
    private String roleDesc;
    private List<Permission> permissions;

    public List<UsersInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UsersInfo> users) {
        this.users = users;
    }

    private List<UsersInfo> users;
}
