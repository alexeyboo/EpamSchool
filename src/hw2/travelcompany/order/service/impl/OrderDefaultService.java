package hw2.travelcompany.order.service.impl;

import hw2.travelcompany.order.domain.Order;
import hw2.travelcompany.order.repo.OrderRepo;
import hw2.travelcompany.order.service.OrderService;

public class OrderDefaultService implements OrderService {

    private final OrderRepo orderRepo;

    public OrderDefaultService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public void add(Order order) {
        if (order != null) {
            orderRepo.add(order);
        }
    }

    public Order findById(Long id) {
        if (id != null) {
            return orderRepo.findById(id);
        } else
            return null;
    }

    public void deleteById(Long id) {
        if (id != null) {
            orderRepo.deleteById(id);
        }
    }

    public void delete(Order order) {
        if (order != null) {
            this.deleteById(order.getId());
        }
    }

    public void printAll() {
        orderRepo.printAll();
    }
}
