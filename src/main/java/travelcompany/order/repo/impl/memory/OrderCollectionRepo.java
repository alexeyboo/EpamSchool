package travelcompany.order.repo.impl.memory;

import travelcompany.common.business.search.Paginator;
import travelcompany.order.domain.Order;
import travelcompany.order.repo.OrderRepo;
import travelcompany.order.repo.impl.OrderSortingComponent;
import travelcompany.order.search.OrderSearchCondition;
import travelcompany.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static travelcompany.common.solutions.utils.CollectionUtils.getPageableData;
import static travelcompany.storage.Storage.ordersList;

public class OrderCollectionRepo implements OrderRepo {
    private OrderSortingComponent orderingComponent = new OrderSortingComponent();

    @Override
    public Order insert(Order order) {
        ordersList.add(order);
        order.setId(SequenceGenerator.getNextValue());

        return order;
    }

    @Override
    public void insert(Collection<Order> orders) {
        for (Order order : orders) {
            insert(order);
        }
    }

    @Override
    public void update(Order entity) {}

    @Override
    public Order findById(Long id) {
        return findOrderById(id);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        List<Order> result = doSearch(searchCondition);

        boolean needSorting = !result.isEmpty() && searchCondition.needSorting();
        boolean shouldPaginate = !result.isEmpty() && searchCondition.shouldPaginate();

        if (needSorting) {
            orderingComponent.applySorting(result, searchCondition);
        }

        if (shouldPaginate) {
            result = getPageableOrderData(result, searchCondition.getPaginator());
        }

        return result;
    }

    private List<Order> getPageableOrderData(List<Order> result, Paginator paginator) {
        return getPageableData(result, paginator.getLimit(), paginator.getOffset());
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
    public int countAll() {
        return ordersList.size();
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
