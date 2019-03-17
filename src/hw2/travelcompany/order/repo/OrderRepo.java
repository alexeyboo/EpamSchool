package hw2.travelcompany.order.repo;

import hw2.travelcompany.common.solutions.repo.BaseRepo;
import hw2.travelcompany.order.domain.Order;
import hw2.travelcompany.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo {
    void add(Order order);

    Order findById(long id);

    List<Order> search(OrderSearchCondition searchCondition);

    void update(Order order);
}
