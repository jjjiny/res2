package ssm.service.impl;

import entity.Role;
import entity.UsersInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.dao.UserDao;
import ssm.service.UserService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

   /* @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UsersInfo usersInfo = userDao.findUserByName(s);
        //自己的对象封装成UserDetails(User为实现类)
        User user = new User(usersInfo.getUsername(), "{noop}" + usersInfo.getPassword(), usersInfo.getStatus() == 0 ? true : false, true, true, true, getAuthority(usersInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roleList) {
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roleList) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }*/

    @Override
    public List<UsersInfo> findAll() {
        return userDao.findAllUsers();
    }

    @Override
    public int save(UsersInfo usersInfo) {
        return userDao.save(usersInfo);
    }

    @Override
    public UsersInfo findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findRoleByid(String id) {
        return userDao.findRoleByid(id);
    }

    @Override
    public void saveRole(String userid, String[] roleId) {
        for (String id : roleId) {
            userDao.saveRole(userid, id);
        }

    }
}
