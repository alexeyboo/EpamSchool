package travelcompany.city.domain.impl;

import travelcompany.city.domain.*;
import travelcompany.city.domain.typesofcities.*;

public class SightseeAndSkiResortCity extends City implements Sightseeable, SkiResortable {

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

    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.SIGHTSEE_N_SKI_RESORT;
    }
}
