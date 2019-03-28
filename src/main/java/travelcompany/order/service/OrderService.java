package travelcompany.order.service;

<<<<<<< HEAD
import travelcompany.common.solutions.service.BaseService;
import travelcompany.order.domain.Order;
import travelcompany.order.search.OrderSearchCondition;
=======
import hw2.travelcompany.common.solutions.service.BaseService;
import hw2.travelcompany.order.domain.Order;
import hw2.travelcompany.order.search.OrderSearchCondition;
>>>>>>> github/master

import java.util.List;

public interface OrderService extends BaseService <Order, Long>{

    List<? extends Order> search(OrderSearchCondition searchCondition);

    void deleteByUserId(Long userId);

    List<Order> getOrdersByUser(Long userId);

}
