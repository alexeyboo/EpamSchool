package hw2.travelcompany.country.repo.impl;

import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.search.CountryOrderByField;
import hw2.travelcompany.country.search.CountrySearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryOrderingComponent {
//    class MyComparable implements Comparable<String> {
//
//        private String srcString;
//        private boolean invert = false;
//
//        public MyComparable(String srcString) {
//            this.srcString = srcString;
//        }
//
//        @Override
//        public int compareTo(String o) {
//            if (invert) {
//                return (-1) * this.srcString.compareTo(o);
//            } else {
//                return this.srcString.compareTo(o);
//            }
//        }
//    }
    public void applyOrdering(List<Country> countries, CountrySearchCondition countrySearchCondition) {
        Comparator<Country> countryComparator = null;

        CountryOrderByField field = countrySearchCondition.getOrderByField();
        switch (countrySearchCondition.getOrderType()) {
            case SIMPLE:
                countryComparator = CountryComparatorComponent.getInstance().getComparatorForField(field);
                break;
            case COMPLEX:
                countryComparator = CountryComparatorComponent.getInstance().getComplexComparator(field);
                break;
        }

        if (countryComparator != null) {
            switch (countrySearchCondition.getOrderDirection()) {
                case ASC:
                    Collections.sort(countries, countryComparator);
                    break;
                case DESC:
                    Collections.sort(countries, countryComparator.reversed());
                    break;
            }
        }
    }
}
