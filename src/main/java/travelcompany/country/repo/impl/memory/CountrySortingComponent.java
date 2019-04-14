package travelcompany.country.repo.impl.memory;

import travelcompany.country.domain.Country;
import travelcompany.country.search.CountrySortByField;
import travelcompany.country.search.CountrySearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountrySortingComponent {

    public void applySorting(List<Country> countries, CountrySearchCondition countrySearchCondition) {
        Comparator<Country> countryComparator = null;
        CountrySortByField field = countrySearchCondition.getSortByField();

        switch (countrySearchCondition.getSortType()) {
            case SIMPLE:
                countryComparator = CountryComparatorComponent.getInstance().getComparatorForField(field);
                break;
            case COMPLEX:
                countryComparator = CountryComparatorComponent.getInstance().getComplexComparator(field);
                break;
        }

        if (countryComparator != null) {
            switch (countrySearchCondition.getSortDirection()) {
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
