package travelcompany.city.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum CityDiscriminator {
    BEACH, SKI_RESORT, SIGHTSEE, BEACH_N_SKI_RESORT, BEACH_N_SIGHTSEE,
    SIGHTSEE_N_SKI_RESORT, BEACH_N_SIGHTSEE_N_SKI_RESORT;

    static Map<String, CityDiscriminator> stringCityDiscriminatorMap = new HashMap<>();

    static {
        for (CityDiscriminator discriminator : CityDiscriminator.values()) {
            stringCityDiscriminatorMap.put(discriminator.name(), discriminator);
        }
    }

    public static Optional<CityDiscriminator> getDiscriminatorByName(String discriminatorName) {
        return Optional.ofNullable(stringCityDiscriminatorMap.get(discriminatorName));
    }
}
