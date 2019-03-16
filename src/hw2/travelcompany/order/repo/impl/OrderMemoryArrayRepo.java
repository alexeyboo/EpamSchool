package hw2.travelcompany.order.repo.impl;

import hw2.travelcompany.order.domain.Order;
import hw2.travelcompany.order.repo.OrderRepo;
import hw2.travelcompany.order.search.OrderSearchCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hw2.travelcompany.storage.Storage.orders;

public class OrderMemoryArrayRepo implements OrderRepo {
    private int orderIndex = 0;

    public void add(Order order) {
        if (orderIndex == orders.length) {
            Order[] newOrders = new Order[orders.length * 2];
            System.arraycopy(orders, 0, newOrders, 0, orders.length);
            orders = newOrders;
        }
        orders[orderIndex] = order;
        orderIndex++;
    }

    public Order findById(long id) {
        Integer orderIndex = findIndexById(id);
        if (orderIndex != null) {
            return orders[orderIndex];
        }
        return null;
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            List<Order> result = doSearch(searchCondition);
        }
    }

    private List<Order> doSearch(OrderSearchCondition searchCondition) {
        boolean searchByUser = searchCondition.getUser() != null;
        boolean searchByCity = searchCondition.getCity() != null;
        boolean searchByCountry = searchCondition.getCountry() != null;
        boolean searchByPrice = searchCondition.getPrice() != null;

        Order[] result = new Order[orders.length];
        int resultIndex = 0;

        for (Order order : orders) {
            if (order != null) {
                boolean found = true;
                if (searchByCity) {
                    for (int i = 0; i < order.getCities().length; i++) {
                        if (searchCondition.getCity().equals(order.getCities()[i])) {
                            found = true;
                            break;
                        }
                        found = false;
                    }
                }
                if (found && searchByUser) {
                    for (int i = 0; i < order.getUsers().length; i++) {
                        if (searchCondition.getUser().equals(order.getUsers()[i])) {
                            found = true;
                            break;
                        }
                        found = false;
                    }
                }
                if (found && searchByCountry) {
                    for (int i = 0; i < order.getCountries().length; i++) {
                        if (searchCondition.getCountry().equals(order.getCountries()[i])) {
                            found = true;
                            break;
                        }
                        found = false;
                    }
                }
                if (found && searchByPrice) {
                    found = searchCondition.getPrice().equals(order.getPrice());
                }
                if (found) {
                    result[resultIndex] = order;
                    resultIndex++;
                }
            }
        }
        if (resultIndex > 0) {
            Order toReturn[] = new Order[resultIndex];
            System.arraycopy(result, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));

        }
    }

    @Override
    public void update(Order order) {

    }

    public void deleteById(long id) {
        Integer orderIndex = findIndexById(id);
        if (orderIndex != null)
            deleteOrderByIndex(orderIndex);
    }

    public void deleteOrder(Order order) {
        Integer orderIndex = findIndexByEntity(order);
        if (orderIndex != null)
            deleteOrderByIndex(orderIndex);
    }

    public void printAll() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private void deleteOrderByIndex(Integer orderIndex) {
        Order[] newOrders = new Order[orders.length - 1];
        System.arraycopy(orders, 0, newOrders, 0, orderIndex);
        System.arraycopy(orders, orderIndex + 1, newOrders, orderIndex, orders.length - orderIndex);
        orders = newOrders;
        orderIndex--;
    }

    private Integer findIndexById(Long id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId().equals(id))
                return i;
        }
        return null;
    }

    private Integer findIndexByEntity(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].equals(order))
                return i;
        }
        return null;
    }
}
