package hw2.travelcompany.city.repo.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.search.CityOrderByField;
import hw2.travelcompany.city.search.CitySearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CityOrderingComponent {

    public void applyOrdering(List<City> cities, CitySearchCondition citySearchCondition) {
        Comparator<City> cityComparator = null;
        CityOrderByField field = citySearchCondition.getOrderByField();
        switch (citySearchCondition.getOrderType()) {
            case SIMPLE:
                cityComparator = CityComparatorComponent.getInstance().getComparatorForField(field);
                break;
            case COMPLEX:
                cityComparator = CityComparatorComponent.getInstance().getComplexComparator(field);
                break;
        }
        if (cityComparator != null) {
            switch (citySearchCondition.getOrderDirection()) {
                case DESC:
                    Collections.sort(cities, cityComparator);
                    break;
                case ASC:
                    Collections.sort(cities, cityComparator.reversed());
                    break;
            }
        }
    }
}
