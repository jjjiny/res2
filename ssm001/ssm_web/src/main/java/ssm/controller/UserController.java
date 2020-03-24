package ssm.controller;

import entity.Role;
import entity.UsersInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm.service.UserService;
import utils.Encrypt;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    //查所有用户
    @RequestMapping("findAll.do")
    public ModelAndView findAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        List<UsersInfo> list = userService.findAll();
        modelAndView.addObject("userList", list);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    //添加一个用户
    @RequestMapping("save.do")
    public String addUser(UsersInfo usersInfo) {
        usersInfo.setPassword(Encrypt.encryptPassword(usersInfo.getPassword()));
        userService.save(usersInfo);
        return "redirect:findAll.do";
    }

    //查询一个用户
    @RequestMapping("findById.do")
    public ModelAndView findById(String id) {
        ModelAndView modelAndView = new ModelAndView();
        UsersInfo usersInfo = userService.findById(id);
        modelAndView.addObject("user", usersInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    //根据用户查可以添加的角色
    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) {
        ModelAndView modelAndView = new ModelAndView();
        //根据id查用户
        UsersInfo usersInfo = userService.findById(id);
        //根据id查用户可添加的角色
        List<Role> roleList = userService.findRoleByid(id);
        modelAndView.addObject("user", usersInfo);
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    //添加用户的角色
    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(String userId, @RequestParam(name = "ids") String[] roleid) {
        System.out.println(userId);
        userService.saveRole(userId, roleid);
        return "redirect:findAll.do";
    }

}
