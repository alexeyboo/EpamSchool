package hw2.travelcompany.city.domain;

import java.util.HashMap;
import java.util.Map;

public enum CityDiscriminator {
    BEACH, SKI_RESORT, SIGHTSEE, BEACH_N_SKI_RESORT, BEACH_N_SIGHTSEE, SIGHTSEE_N_SKI_RESORT, BEACH_N_SIGHTSEE_N_SKI_RESORT;

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
