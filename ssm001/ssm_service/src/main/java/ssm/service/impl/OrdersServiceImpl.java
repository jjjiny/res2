package ssm.service.impl;

import com.github.pagehelper.PageHelper;
import entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.dao.OrdersDao;
import ssm.dao.ProductDao;
import ssm.service.OrdersService;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findallOrders(int page, int size) {
        PageHelper.startPage(page, size);
        return ordersDao.findallOrders();
    }

    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }
}
