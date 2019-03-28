package travelcompany.city.domain.impl;

import travelcompany.city.domain.*;
import travelcompany.city.domain.typesofcities.*;

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
