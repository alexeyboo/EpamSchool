package travelcompany.city.domain.impl;

import travelcompany.city.domain.*;
import travelcompany.city.domain.typesofcities.*;

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
