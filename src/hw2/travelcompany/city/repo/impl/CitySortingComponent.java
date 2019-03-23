package hw2.travelcompany.city.repo.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.search.CitySortByField;
import hw2.travelcompany.city.search.CitySearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CitySortingComponent {

    public void applySorting(List<? extends City> cities, CitySearchCondition citySearchCondition) {
        Comparator<City> cityComparator = null;
        CitySortByField field = citySearchCondition.getSortByField();
        switch (citySearchCondition.getSortType()) {
            case SIMPLE:
                cityComparator = CityComparatorComponent.getInstance().getComparatorForField(field);
                break;
            case COMPLEX:
                cityComparator = CityComparatorComponent.getInstance().getComplexComparator(field);
                break;
        }
        if (cityComparator != null) {
            switch (citySearchCondition.getSortDirection()) {
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
