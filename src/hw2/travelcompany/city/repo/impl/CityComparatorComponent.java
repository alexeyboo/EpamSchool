package hw2.travelcompany.city.repo.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.domain.typesofcities.BeachCity;
import hw2.travelcompany.city.domain.typesofcities.SightseeCity;
import hw2.travelcompany.city.domain.typesofcities.SkiResortCity;
import hw2.travelcompany.city.search.CitySortByField;

import java.util.*;

import static hw2.travelcompany.city.search.CitySortByField.*;
import static hw2.travelcompany.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;

public final class CityComparatorComponent {
    public static final CityComparatorComponent INSTANCE = new CityComparatorComponent();
    private static Map<CitySortByField, Comparator<City>> comparatorByField = new HashMap<>();
    private static Set<CitySortByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAME, POPULATION, COUNTRY, CLIMATE, IS_CAPITAL));

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
                    if (result == 0){
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
                BeachCity c1 = (BeachCity) o1;
                BeachCity c2 = (BeachCity) o2;
                if (c1.getNumOfBeaches() > c2.getNumOfBeaches())
                    return 1;
                if (c1.getNumOfBeaches() == c2.getNumOfBeaches())
                    return 0;
                return -1;
            }
        };
    }
    private static Comparator<City> getComparatorForNumOfSightsField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                SightseeCity c1 = (SightseeCity) o1;
                SightseeCity c2 = (SightseeCity) o2;
                if (c1.getNumOfSights() > c2.getNumOfSights())
                    return 1;
                if (c1.getNumOfSights() == c2.getNumOfSights())
                    return 0;
                return -1;
            }
        };
    }
    private static Comparator<City> getComparatorForNumOfSkiResortsField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                SkiResortCity c1 = (SkiResortCity) o1;
                SkiResortCity c2 = (SkiResortCity) o2;
                if (c1.getNumOfSkiResorts() > c2.getNumOfSkiResorts())
                    return 1;
                if (c1.getNumOfSkiResorts() == c2.getNumOfSkiResorts())
                    return 0;
                return -1;
            }
        };
    }

    private static Comparator<City> getComparatorForIsCapitalField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                if (o1.isCapital() == true)
                    return 1;
                if (o2.isCapital() == true)
                    return -1;
                return 0;
            }
        };
    }

    private static Comparator<City> getComparatorForClimateField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableStrings().compare(o1.getClimate().getClass().getName(), o2.getClimate().getClass().getName());
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
                if (o1.getPopulation() > o2.getPopulation())
                    return 1;
                if (o1.getPopulation() == o2.getPopulation())
                    return 0;
                return -1;
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
