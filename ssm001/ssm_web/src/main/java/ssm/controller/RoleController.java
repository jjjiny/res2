package ssm.controller;

import entity.Orders;
import entity.Permission;
import entity.Role;
import entity.UsersInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm.service.RoleService;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll() {
        List<Role> roleList = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //根据角色查可以添加的权限
    @RequestMapping("findRoleByIdAndAllPermission.do")
    public ModelAndView findUserByIdAndAllRole(String id) {
        ModelAndView modelAndView = new ModelAndView();
        //根据id查角色
        Role role = roleService.findById(id);
        //根据id查用户可添加的角色
        List<Permission> permissionList = roleService.findPermissionByid(id);
        modelAndView.addObject("role", role);
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    //添加角色的权限
    @RequestMapping("addPermissionToRole.do")
    public String addRoleToUser(String roleId, @RequestParam(name = "ids") String[] permissionid) {
        roleService.savePermissionToRole(roleId, permissionid);
        return "redirect:findAll.do";
    }
}
