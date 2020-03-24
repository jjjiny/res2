package ssm.controller;

import com.github.pagehelper.PageInfo;
import entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm.service.OrdersService;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("findAll.do")
    public ModelAndView findall(@RequestParam(required = true, defaultValue = "1") Integer page, @RequestParam(required = true, defaultValue = "3") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        List<Orders> ordersList = ordersService.findallOrders(page, size);
        /*PageInfo为一个分页bean*/
        PageInfo pageInfo = new PageInfo(ordersList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }

    @RequestMapping("findById.do")
    public ModelAndView findById(@RequestParam(required = true) String id) {
        Orders order = ordersService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", order);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }


}
