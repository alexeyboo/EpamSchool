package travelcompany.country.repo.impl;

import travelcompany.country.domain.Country;
import travelcompany.country.search.CountrySortByField;

import java.util.*;

import static travelcompany.common.business.repo.CommonComparatorHolder.getComparatorForNullableStrings;
import static travelcompany.country.search.CountrySortByField.*;

public final class CountryComparatorComponent {
    private static final CountryComparatorComponent INSTANCE = new CountryComparatorComponent();
    private static Map<CountrySortByField, Comparator<Country>> comparatorsByField = new HashMap<>();

    //for complex
    private static Set<CountrySortByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAME, LANGUAGE));

    private CountryComparatorComponent() {
    }

    static {
        comparatorsByField.put(NAME, getComparatorForNameField());
        comparatorsByField.put(LANGUAGE, getComparatorForLanguageField());
    }

    public static CountryComparatorComponent getInstance() {
        return INSTANCE;
    }

    public Comparator<Country> getComparatorForField(CountrySortByField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<Country> getComplexComparator(CountrySortByField field) {
        return new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                int result = 0;
                Comparator<Country> countryComparator = comparatorsByField.get(field);

                if (countryComparator != null) {
                    result = countryComparator.compare(o1, o2);

                    //if records have the same order priority, i want to order them in their group
                    if (result == 0) {

                        //loop through all possible sorting fields
                        for (CountrySortByField otherField : fieldComparePriorityOrder) {

                            //if i haven't sorted by field which is taken from parameter in function, i do sorting
                            if (!otherField.equals(field)) {
                                result = comparatorsByField.get(otherField).compare(o1, o2);

                                //if sort result detected that records are not equal - we exit from loop
                                //else continue
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

    private static Comparator<Country> getComparatorForLanguageField() {
        return new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return getComparatorForNullableStrings().compare(o1.getLanguage(), o2.getLanguage());
            }
        };
    }

    private static Comparator<Country> getComparatorForNameField() {
        return new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return getComparatorForNullableStrings().compare(o1.getName(), o2.getName());
            }
        };
    }

}
