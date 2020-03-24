package ssm.service;

import entity.Role;
import entity.UsersInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService /*extends UserDetailsService*/ {
    public List<UsersInfo> findAll();

    public int save(UsersInfo usersInfo);

    UsersInfo findById(String id);

    List<Role> findRoleByid(String id);

    void saveRole(String userid, String[] roleId);
}
