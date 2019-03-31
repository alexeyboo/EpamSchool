package travelcompany.city.domain.impl;

import travelcompany.city.domain.*;
import travelcompany.city.domain.typesofcities.*;

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

    @Override
    public String toString() {
        return super.toString() +
                ", numOfBeaches=" + numOfBeaches +
                ", numOfSights=" + numOfSights + '\'';
    }
}
