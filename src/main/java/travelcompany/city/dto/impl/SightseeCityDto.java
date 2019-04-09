package travelcompany.city.dto.impl;

import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.dto.CityDto;
import travelcompany.city.dto.typesofcities.SightseeableDto;

public class SightseeCityDto extends CityDto implements SightseeableDto {
    private int numOfSights;

    public int getNumOfSights() {
        return numOfSights;
    }

    public void setNumOfSights(int numOfSights) {
        this.numOfSights = numOfSights;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.SIGHTSEE;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", numOfSights=" + numOfSights + '\'';
    }
}
