package travelcompany.order.service;

import travelcompany.common.solutions.service.BaseService;
import travelcompany.order.domain.Order;
import travelcompany.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService <Order, Long>{
    List<? extends Order> search(OrderSearchCondition searchCondition);

    void deleteByUserId(Long userId);

    List<Order> getOrdersByUser(Long userId);
}
