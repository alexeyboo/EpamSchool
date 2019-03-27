package travelcompany.order.repo.impl;

import hw2.travelcompany.common.solutions.utils.ArrayUtils;
import hw2.travelcompany.order.domain.Order;
import hw2.travelcompany.order.repo.OrderRepo;
import hw2.travelcompany.order.search.OrderSearchCondition;
import hw2.travelcompany.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hw2.travelcompany.storage.Storage.ordersArray;

public class OrderMemoryArrayRepo implements OrderRepo {
    private int orderIndex = 0;

    public void insert(Order order) {
        if (orderIndex == ordersArray.length) {
            Order[] newOrders = new Order[ordersArray.length * 2];
            System.arraycopy(ordersArray, 0, newOrders, 0, ordersArray.length);
            ordersArray = newOrders;
        }
        ordersArray[orderIndex] = order;
        order.setId(SequenceGenerator.getNextValue());
        orderIndex++;
    }

    @Override
    public void update(Order order) {

    }

    public Order findById(Long id) {
        Integer orderIndex = findIndexById(id);
        if (orderIndex != null) {
            return ordersArray[orderIndex];
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Integer orderIndex = findIndexById(id);
        if (orderIndex != null)
            deleteOrderByIndex(orderIndex);
    }

    private void deleteOrderByIndex(Integer orderIndex) {
        ArrayUtils.removeElement(ordersArray, orderIndex);
        orderIndex--;
    }

    public void printAll() {
        for (Order order : ordersArray) {
            System.out.println(order);
        }
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(Arrays.asList(ordersArray));
    }

    private Integer findIndexById(long id) {
        for (int i = 0; i < ordersArray.length; i++) {
            if (ordersArray[i].getId().equals(id))
                return i;
        }
        return null;
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

        Order[] result = new Order[ordersArray.length];
        int resultIndex = 0;

        for (Order order : ordersArray) {
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
        return Collections.emptyList();
    }

    @Override
    public int countByCity(long cityId) {
        int count = 0;
        for (Order order : ordersArray) {
            if (order.getCity().getId().equals(cityId)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countByCountry(long countryId) {
        int count = 0;
        for (Order order : ordersArray) {
            if (order.getCountry().getId().equals(countryId)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Order> findByUserId(long userId) {
        List<Order> foundOrders = new ArrayList<>();

        for (Order order : ordersArray) {
            if (order.getUser().getId().equals(userId)) {
                foundOrders.add(order);
            }
        }
        return foundOrders;
    }

    @Override
    public void deleteByUserId(long userId) {
        for (Order order : findByUserId(userId)) {
            ArrayUtils.removeElement(ordersArray, findIndexById(order.getId()));
        }
    }


}
