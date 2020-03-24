package ssm.dao;

import entity.Traveller;
import org.apache.ibatis.annotations.Select;

public interface travellerDao {
    @Select("select * from traveller where id in (select travellerid from order_traveller where orderid =#{id})")
    public Traveller findById(String id);
}
