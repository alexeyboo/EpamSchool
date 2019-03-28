package travelcompany.city.repo.impl;

<<<<<<< HEAD
=======

>>>>>>> github/master
import travelcompany.city.domain.City;
import travelcompany.city.domain.typesofcities.Beachable;
import travelcompany.city.domain.typesofcities.Sightseeable;
import travelcompany.city.domain.typesofcities.SkiResortable;
import travelcompany.city.search.CitySortByField;

import java.util.*;

import static travelcompany.city.search.CitySortByField.*;
<<<<<<< HEAD
import static travelcompany.common.business.repo.CommonComparatorHolder.*;
=======
import static travelcompany.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableBooleans;
import static travelcompany.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableIntegers;
import static travelcompany.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;

>>>>>>> github/master

public final class CityComparatorComponent {
    public static final CityComparatorComponent INSTANCE = new CityComparatorComponent();
    private static Map<CitySortByField, Comparator<City>> comparatorByField = new HashMap<>();

    //for complex
    private static Set<CitySortByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays
            .asList(NAME, POPULATION, COUNTRY, CLIMATE, IS_CAPITAL, NUM_OF_BEACHES, NUM_OF_SIGHTS, NUM_OF_SKIRESORTS));

    public static CityComparatorComponent getInstance() {
        return INSTANCE;
    }

    private CityComparatorComponent() {
    }

    public Comparator<City> getComparatorForField(CitySortByField field) {
        return comparatorByField.get(field);
    }

    public Comparator<City> getComplexComparator(CitySortByField field) {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                int result = 0;
                Comparator<City> cityComparator = comparatorByField.get(field);
                if (cityComparator != null) {
                    result = cityComparator.compare(o1, o2);
                    //if records have same order priority, i want to order them in their group
                    if (result == 0) {
                        //loop through all possible sorting fields
                        for (CitySortByField otherField : fieldComparePriorityOrder) {
                            //if i haven't sorted by field which is taken from parameter in function, i do sorting
                            if (!otherField.equals(field)) {
                                result = comparatorByField.get(otherField).compare(o1, o2);
                                //if sort result detected that records are not equal - we exit from loop,
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

    static {
        comparatorByField.put(NAME, getComparatorForNameField());
        comparatorByField.put(POPULATION, getComparatorForPopulationField());
        comparatorByField.put(COUNTRY, getComparatorForCountryField());
        comparatorByField.put(CLIMATE, getComparatorForClimateField());
        comparatorByField.put(IS_CAPITAL, getComparatorForIsCapitalField());
        comparatorByField.put(NUM_OF_BEACHES, getComparatorForNumOfBeachesField());
        comparatorByField.put(NUM_OF_SIGHTS, getComparatorForNumOfSightsField());
        comparatorByField.put(NUM_OF_SKIRESORTS, getComparatorForNumOfSkiResortsField());
    }

    private static Comparator<City> getComparatorForNumOfBeachesField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableIntegers()
                        .compare(((Beachable) o1).getNumOfBeaches(), ((Beachable) o2).getNumOfBeaches());
            }
        };
    }

    private static Comparator<City> getComparatorForNumOfSightsField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableIntegers()
                        .compare(((Sightseeable) o1).getNumOfSights(), ((Sightseeable) o2).getNumOfSights());
            }
        };
    }

    private static Comparator<City> getComparatorForNumOfSkiResortsField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableIntegers()
                        .compare(((SkiResortable) o1).getNumOfSkiResorts(), ((SkiResortable) o2).getNumOfSkiResorts());
            }
        };
    }

    private static Comparator<City> getComparatorForIsCapitalField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableBooleans().compare(o1.getIsCapital(), o2.getIsCapital());
            }
        };
    }

    private static Comparator<City> getComparatorForClimateField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableStrings()
                        .compare(o1.getClimate().getClass().getName(), o2.getClimate().getClass().getName());
            }
        };
    }

    private static Comparator<City> getComparatorForCountryField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableStrings().compare(o1.getCountry().getName(), o2.getCountry().getName());
            }
        };
    }

    private static Comparator<City> getComparatorForPopulationField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableIntegers().compare(o1.getPopulation(), o2.getPopulation());
            }
        };
    }

    private static Comparator<City> getComparatorForNameField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableStrings().compare(o1.getName(), o2.getName());
            }
        };
    }
}
