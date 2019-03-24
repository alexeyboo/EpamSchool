package hw2.travelcompany.city.domain.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.domain.CityDiscriminator;
import hw2.travelcompany.city.domain.typesofcities.Beachable;
import hw2.travelcompany.city.domain.typesofcities.Sightseeable;

public class BeachAndSightseeCity extends City implements Beachable, Sightseeable {

    private int numOfBeaches;
    private int numOfSights;

    public int getNumOfSights() {
        return numOfSights;
    }

    public void setNumOfSights(int numOfSights) {
        this.numOfSights = numOfSights;
    }

    public int getNumOfBeaches() {
        return numOfBeaches;
    }

    public void setNumOfBeaches(int numOfBeaches) {
        this.numOfBeaches = numOfBeaches;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.BEACH_N_SIGHTSEE;
    }
}
