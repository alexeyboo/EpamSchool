package hw2.travelcompany.country.repo.impl;

import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.search.CountryOrderByField;

import java.util.*;

import static hw2.travelcompany.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static hw2.travelcompany.country.search.CountryOrderByField.*;

public final class CountryComparatorComponent {
    private static final CountryComparatorComponent INSTANCE = new CountryComparatorComponent();
    private static Map<CountryOrderByField, Comparator<Country>> comparatorsByField = new HashMap<>();
    private static Set<CountryOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAME, LANGUAGE));

    private CountryComparatorComponent() {
    }

    public static CountryComparatorComponent getInstance() {
        return INSTANCE;
    }

    static {
        comparatorsByField.put(NAME, getComparatorForNameField());
        comparatorsByField.put(LANGUAGE, getComparatorForLanguageField());
    }

    public Comparator<Country> getComparatorForField(CountryOrderByField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<Country> getComplexComparator(CountryOrderByField field) {
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
                        for (CountryOrderByField otherField : fieldComparePriorityOrder) {
                            //if i haven't sorted by field which is taken from parameter in function, i do sorting
                            if (!otherField.equals(field)) {
                                result = comparatorsByField.get(otherField).compare(o1, o2);
                                //if sort result detected that records are not equal - we exit from loop
                                //else continue
                                if (result != 0) {
                                    break;//continue???
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
