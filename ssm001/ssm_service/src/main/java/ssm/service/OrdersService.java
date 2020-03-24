package ssm.service;

import entity.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrdersService {
    List<Orders> findallOrders(int page,int size);

    Orders findById(String id);
}
