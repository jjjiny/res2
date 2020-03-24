package ssm.dao;

import entity.Member;
import entity.Orders;
import entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao {
    @Select("select * from orders")
    @Results({
         /*   @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),*/
            @Result(property = "product", column = "productid", javaType = Product.class, one = @One(select = "ssm.dao.ProductDao.findById"))

    })
    public List<Orders> findallOrders();

    @Select("select * from orders where id=#{id}")
    @Results({
         /*   @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),*/
            @Result(property = "product", column = "productid", javaType = Product.class, one = @One(select = "ssm.dao.ProductDao.findById")),
            @Result(property = "member", column = "memberid", javaType = Member.class, one = @One(select = "ssm.dao.MemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = List.class, many = @Many(select = "ssm.dao.travellerDao.findById"))
    })
    public Orders findById(String id);
}
