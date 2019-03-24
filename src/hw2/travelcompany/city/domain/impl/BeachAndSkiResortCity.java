package hw2.travelcompany.city.domain.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.domain.CityDiscriminator;
import hw2.travelcompany.city.domain.typesofcities.Beachable;
import hw2.travelcompany.city.domain.typesofcities.SkiResortable;

public class BeachAndSkiResortCity extends City implements Beachable, SkiResortable {

    private int numOfBeaches;
    private int numOfSkiResorts;

    public int getNumOfSkiResorts() {
        return numOfSkiResorts;
    }

    public void setNumOfSkiResorts(int numOfSkiResorts) {
        this.numOfSkiResorts = numOfSkiResorts;
    }

    public int getNumOfBeaches() {
        return numOfBeaches;
    }

    public void setNumOfBeaches(int numOfBeaches) {
        this.numOfBeaches = numOfBeaches;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.BEACH_N_SKI_RESORT;
    }
}
