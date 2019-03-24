package hw2.travelcompany.city.domain.impl;

import hw2.travelcompany.city.domain.*;
import hw2.travelcompany.city.domain.typesofcities.Beachable;
import hw2.travelcompany.city.domain.typesofcities.Sightseeable;
import hw2.travelcompany.city.domain.typesofcities.SkiResortable;

public class BeachAndSightseeAndSkiResortCity extends City implements Beachable, Sightseeable, SkiResortable {

    private int numOfBeaches;
    private int numOfSights;
    private int numOfSkiResorts;

    public int getNumOfSkiResorts() {
        return numOfSkiResorts;
    }

    public void setNumOfSkiResorts(int numOfSkiResorts) {
        this.numOfSkiResorts = numOfSkiResorts;
    }

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
        discriminator = CityDiscriminator.BEACH_N_SIGHTSEE_N_SKI_RESORT;
    }
}
