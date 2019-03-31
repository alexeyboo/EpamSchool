package travelcompany.order.repo;

import travelcompany.common.solutions.repo.BaseRepo;
import travelcompany.order.domain.Order;
import travelcompany.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo <Order, Long>{
    List<Order> findByUserId(long userId);

    void deleteByUserId(long userId);

    List<Order> search(OrderSearchCondition searchCondition);

    int countByCountry(long countryId);

    int countByCity(long cityId);
}
