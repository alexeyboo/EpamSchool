package travelcompany.city.domain.impl;

import travelcompany.city.domain.City;
import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.domain.typesofcities.Beachable;
import travelcompany.city.domain.typesofcities.SkiResortable;

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
