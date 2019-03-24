package hw2.travelcompany.city.domain.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.domain.CityDiscriminator;
import hw2.travelcompany.city.domain.typesofcities.Beachable;

public class BeachCity extends City implements Beachable {

    private int numOfBeaches;

    public int getNumOfBeaches() {
        return numOfBeaches;
    }

    public void setNumOfBeaches(int numOfBeaches) {
        this.numOfBeaches = numOfBeaches;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.BEACH;
    }
}
