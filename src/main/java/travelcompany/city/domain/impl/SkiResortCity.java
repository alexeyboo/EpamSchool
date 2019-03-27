package travelcompany.city.domain.impl;

import travelcompany.city.domain.City;
import travelcompany.city.domain.typesofcities.SkiResortable;

public class SkiResortCity extends City implements SkiResortable {

    private int numOfSkiResorts;

    public int getNumOfSkiResorts() {
        return numOfSkiResorts;
    }

    public void setNumOfSkiResorts(int numOfSkiResorts) {
        this.numOfSkiResorts = numOfSkiResorts;
    }

    @Override
    protected void initDiscriminator() {

    }
}
