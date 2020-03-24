package ssm.controller;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ssm.service.ProductService;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //查全部商品
    @RequestMapping("findAll.do")
    public ModelAndView findall() {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.findAllProduct();
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    //增加商品
    @RequestMapping("save.do")
    public String save(Product product) throws ParseException {
        productService.save(product);
        return "redirect:findAll.do";
    }

}
