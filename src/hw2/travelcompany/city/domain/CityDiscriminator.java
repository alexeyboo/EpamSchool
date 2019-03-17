package hw2.travelcompany.city.domain;

import java.util.HashMap;
import java.util.Map;

public enum CityDiscriminator {
    TYPE_ONE, TYPE_TWO;

    static Map<String, CityDiscriminator> stringCityDiscriminatorMap = new HashMap<>();

    static {
        for (CityDiscriminator discriminator : CityDiscriminator.values()) {
            stringCityDiscriminatorMap.put(discriminator.name(), discriminator);
        }
    }

    public static CityDiscriminator getDiscriminatorByName(String discriminatorName) {
        return stringCityDiscriminatorMap.get(discriminatorName);
    }

    public static boolean isDisciminatorExist(String discriminator) {
        return getDiscriminatorByName(discriminator) != null;
    }

    public static boolean isDisciminatorNotExist(String discriminator) {
        return !isDisciminatorExist(discriminator);
    }
}
