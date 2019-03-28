package travelcompany.order.repo.impl;

<<<<<<< HEAD
import travelcompany.order.domain.Order;
import travelcompany.order.repo.OrderRepo;
import travelcompany.order.search.OrderSearchCondition;
import travelcompany.storage.SequenceGenerator;
=======
import hw2.travelcompany.order.domain.Order;
import hw2.travelcompany.order.repo.OrderRepo;
import hw2.travelcompany.order.search.OrderSearchCondition;
import hw2.travelcompany.storage.SequenceGenerator;
>>>>>>> github/master

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

<<<<<<< HEAD
import static travelcompany.storage.Storage.ordersList;
=======
import static hw2.travelcompany.storage.Storage.ordersList;
>>>>>>> github/master

public class OrderMemoryCollectionRepo implements OrderRepo {
    @Override
    public void insert(Order order) {
        ordersList.add(order);
        order.setId(SequenceGenerator.getNextValue());
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public Order findById(Long id) {
        return findOrderById(id);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            return doSearch(searchCondition);
        }
    }

    private List<Order> doSearch(OrderSearchCondition searchCondition) {

        List<Order> result = new ArrayList<>();

        for (Order order : ordersList) {
            if (order != null) {
                boolean found = true;
                if (searchCondition.searchByCity()) {
                    found = searchCondition.getCity().equals(order.getCity());
                }
                if (found && searchCondition.searchByUser()) {
                    found = searchCondition.getUser().equals(order.getUser());
                }
                if (found && searchCondition.searchByCountry()) {
                    found = searchCondition.getCountry().equals(order.getCountry());
                }
                if (found && searchCondition.searchByPrice()) {
                    found = searchCondition.getPrice().equals(order.getPrice());
                }
                if (found) {
                    result.add(order);
                }
            }
        }

        return result;
    }

    @Override
    public void deleteById(Long id) {
        Order found = findOrderById(id);
        if (found != null) {
            ordersList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (Order order : ordersList) {
            System.out.println(order);
        }
    }

    private Order findOrderById(long id) {
        for (Order order : ordersList) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public int countByCountry(long countryId) {
        int count = 0;
        for (Order order : ordersList) {
            if (order.getCountry().getId().equals(countryId)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countByCity(long cityId) {
        int count = 0;
        for (Order order : ordersList) {
            if  (order.getCity().equals(cityId)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Order> findAll() {
        return ordersList;
    }

    @Override
    public List<Order> findByUserId(long userId) {
        List<Order> foundOrders = new ArrayList<>();
        for (Order order : ordersList) {
            if (order.getUser().getId().equals(userId)) {
                foundOrders.add(order);
            }
        }
        return foundOrders;
    }

    @Override
    public void deleteByUserId(long userId) {
        List<Order> toRemove = findByUserId(userId);
        ordersList.removeAll(toRemove);
    }
}
