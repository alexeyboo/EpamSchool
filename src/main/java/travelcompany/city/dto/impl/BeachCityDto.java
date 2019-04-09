package travelcompany.city.dto.impl;

import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.dto.CityDto;
import travelcompany.city.dto.typesofcities.BeachableDto;

public class BeachCityDto extends CityDto implements BeachableDto {
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

    @Override
    public String toString() {
        return super.toString() +
                ", numOfBeaches=" + numOfBeaches + '\'';
    }
}
