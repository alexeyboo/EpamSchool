package hw2.travelcompany.city.domain.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.domain.CityDiscriminator;
import hw2.travelcompany.city.domain.typesofcities.Sightseeable;
import hw2.travelcompany.city.domain.typesofcities.SkiResortable;

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
