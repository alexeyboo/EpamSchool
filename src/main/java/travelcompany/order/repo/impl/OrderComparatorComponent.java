package travelcompany.order.repo.impl;

import travelcompany.order.domain.Order;
import travelcompany.order.search.OrderSortByField;

import java.util.*;

import static travelcompany.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableIntegers;
import static travelcompany.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static travelcompany.order.search.OrderSortByField.*;

public class OrderComparatorComponent {
    private static final OrderComparatorComponent INSTANCE = new OrderComparatorComponent();
    private static Map<OrderSortByField, Comparator<Order>> comparatorsByField = new HashMap<>();
    /* for complex */
    private static Set<OrderSortByField> fieldComparePriorityOrder
            = new LinkedHashSet<>(Arrays.asList(PRICE, USER, COUNTRY, CITY, DESCRIPTION));

    private OrderComparatorComponent() {}

    static {
        comparatorsByField.put(PRICE, getComparatorForPriceField());
        comparatorsByField.put(USER, getComparatorForUserField());
        comparatorsByField.put(COUNTRY, getComparatorForCountryField());
        comparatorsByField.put(CITY, getComparatorForCityField());
        comparatorsByField.put(DESCRIPTION, getComparatorForDescriptionField());
    }

    public static OrderComparatorComponent getInstance() {
        return INSTANCE;
    }

    public static Comparator<Order> getComparatorForField(OrderSortByField field) {
        return comparatorsByField.get(field);
    }

    public static Comparator<Order> getComplexComparator(OrderSortByField field) {
        return new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                int result = 0;
                Comparator<Order> orderComparator = comparatorsByField.get(field);

                if (orderComparator != null) {
                    result = orderComparator.compare(o1, o2);

                    if (result == 0) {
                        for (OrderSortByField otherField : fieldComparePriorityOrder) {
                            if (!otherField.equals(field)) {
                                result = comparatorsByField.get(otherField).compare(o1, o2);

                                if (result != 0) {
                                    break;
                                }
                            }
                        }
                    }
                }

                return result;
            }
        };
    }

    private static Comparator<Order> getComparatorForDescriptionField() {
        return (o1, o2) -> getComparatorForNullableStrings().compare(o1.getDescription(), o2.getDescription());
    }

    private static Comparator<Order> getComparatorForCityField() {
        return (o1, o2) -> getComparatorForNullableStrings().compare(o1.getCity().getName(), o2.getCity().getName());
    }

    private static Comparator<Order> getComparatorForCountryField() {
        return (o1, o2) -> getComparatorForNullableStrings().compare(o1.getCountry().getName(), o2.getCountry().getName());
    }

    private static Comparator<Order> getComparatorForUserField() {
        return (o1, o2) -> getComparatorForNullableStrings().compare(o1.getUser().getLastName(), o2.getUser().getLastName());
    }

    private static Comparator<Order> getComparatorForPriceField() {
        return (o1, o2) -> getComparatorForNullableIntegers().compare(o1.getPrice(), o2.getPrice());
    }
}
