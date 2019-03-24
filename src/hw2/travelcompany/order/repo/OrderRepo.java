package hw2.travelcompany.order.repo;

import hw2.travelcompany.common.solutions.repo.BaseRepo;
import hw2.travelcompany.order.domain.Order;
import hw2.travelcompany.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo <Order, Long>{

    List<Order> findByUserId(long userId);

    void deleteByUserId(long userId);

    List<Order> search(OrderSearchCondition searchCondition);

    int countByCountry(long countryId);

    int countByCity(long cityId);
}
