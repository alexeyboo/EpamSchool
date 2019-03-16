package hw2.travelcompany.order.repo.impl;

import hw2.travelcompany.order.domain.Order;
import hw2.travelcompany.order.repo.OrderRepo;

import static hw2.travelcompany.storage.Storage.ordersList;

public class OrderMemoryCollectionRepo implements OrderRepo {
    @Override
    public void add(Order order) {
        ordersList.add(order);
    }

    @Override
    public Order findById(long id) {
        return findOrderById(id);
    }

    @Override
    public void deleteById(long id) {
        Order found = findOrderById(id);
        if (found != null)
            ordersList.remove(found);
    }

    @Override
    public void printAll() {
        for (Order order : ordersList) {
            System.out.println(order);
        }
    }

    private Order findOrderById(long id) {
        for (Order order : ordersList) {
            if (order.getId().equals(id))
                return order;
        }
        return null;
    }
}
