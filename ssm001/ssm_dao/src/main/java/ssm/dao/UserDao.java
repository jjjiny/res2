package ssm.dao;

import entity.Role;
import entity.UsersInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "ssm.dao.RoleDao.findRoleByUserid"))
    })
    public UsersInfo findUserByName(String username);

    @Select("select * from users")
    public List<UsersInfo> findAllUsers();

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public int save(UsersInfo usersInfo);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "ssm.dao.RoleDao.findRoleByUserid"))
    }
    )
    UsersInfo findById(String id);

    @Select("select * from role where id not in(select roleid from users_role where userid=#{id})")
    List<Role> findRoleByid(String id);

    @Insert("insert into users_role(userId,roleId) values(#{aa},#{roleid})")
    void saveRole(@Param("aa") String userid, @Param("roleid") String xx);


}
