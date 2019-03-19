package hw2.travelcompany.city.domain.typesofcities;

import hw2.travelcompany.city.domain.Beachable;
import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.domain.CityDiscriminator;

public class BeachCity extends City implements Beachable {

    private int numOfBeaches;

    public int getNumOfBeaches() {
        return numOfBeaches;
    }

    public void setNumOfBeaches(int numOfBeaches) {
        this.numOfBeaches = numOfBeaches;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.BEACH;
    }
}
