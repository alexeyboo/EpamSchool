package travelcompany.order.repo.impl;

import travelcompany.order.domain.Order;
import travelcompany.order.search.OrderSearchCondition;
import travelcompany.order.search.OrderSortByField;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderSortingComponent {
    public void applySorting(List<Order> orders, OrderSearchCondition orderSearchCondition) {
        Comparator<Order> orderComparator = null;
        OrderSortByField field = orderSearchCondition.getSortByField();

        switch (orderSearchCondition.getSortType()) {
            case SIMPLE:
                orderComparator = OrderComparatorComponent.getInstance().getComparatorForField(field);
                break;
            case COMPLEX:
                orderComparator = OrderComparatorComponent.getInstance().getComplexComparator(field);
                break;
        }

        if (orderComparator != null) {
            switch (orderSearchCondition.getSortDirection()) {
                case ASC:
                    Collections.sort(orders, orderComparator);
                    break;
                case DESC:
                    Collections.sort(orders, orderComparator.reversed());
                    break;
            }
        }
    }
}
