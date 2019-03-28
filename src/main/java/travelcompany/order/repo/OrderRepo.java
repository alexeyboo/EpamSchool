package travelcompany.order.repo;

<<<<<<< HEAD
import travelcompany.common.solutions.repo.BaseRepo;
import travelcompany.order.domain.Order;
import travelcompany.order.search.OrderSearchCondition;
=======
import hw2.travelcompany.common.solutions.repo.BaseRepo;
import hw2.travelcompany.order.domain.Order;
import hw2.travelcompany.order.search.OrderSearchCondition;
>>>>>>> github/master

import java.util.List;

public interface OrderRepo extends BaseRepo <Order, Long>{

    List<Order> findByUserId(long userId);

    void deleteByUserId(long userId);

    List<Order> search(OrderSearchCondition searchCondition);

    int countByCountry(long countryId);

    int countByCity(long cityId);
}
