package hw2.travelcompany.city.domain.typesofcities;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.domain.SkiResortable;

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
