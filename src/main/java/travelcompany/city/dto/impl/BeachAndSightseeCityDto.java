package travelcompany.city.dto.impl;

import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.dto.CityDto;
import travelcompany.city.dto.typesofcities.BeachableDto;
import travelcompany.city.dto.typesofcities.SightseeableDto;

public class BeachAndSightseeCityDto extends CityDto implements BeachableDto, SightseeableDto {
    private int numOfBeaches;
    private int numOfSights;

    public int getNumOfSights() {
        return numOfSights;
    }

    public void setNumOfSights(int numOfSights) {
        this.numOfSights = numOfSights;
    }

    public int getNumOfBeaches() {
        return numOfBeaches;
    }

    public void setNumOfBeaches(int numOfBeaches) {
        this.numOfBeaches = numOfBeaches;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.BEACH_N_SIGHTSEE;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", numOfBeaches=" + numOfBeaches +
                ", numOfSights=" + numOfSights + '\'';
    }
}
