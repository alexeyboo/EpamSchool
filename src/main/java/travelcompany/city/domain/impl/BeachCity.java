package travelcompany.city.domain.impl;

import travelcompany.city.domain.*;
import travelcompany.city.domain.typesofcities.Beachable;

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

    @Override
    public String toString() {
        return super.toString() +
                ", numOfBeaches=" + numOfBeaches + '\'';
    }
}
