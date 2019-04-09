package travelcompany.city.dto.impl;

import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.dto.CityDto;
import travelcompany.city.dto.typesofcities.BeachableDto;
import travelcompany.city.dto.typesofcities.SightseeableDto;
import travelcompany.city.dto.typesofcities.SkiResortableDto;

public class BeachAndSightseeAndSkiResortCityDto extends CityDto
        implements BeachableDto, SightseeableDto, SkiResortableDto {
    private int numOfBeaches;
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

    public int getNumOfBeaches() {
        return numOfBeaches;
    }

    public void setNumOfBeaches(int numOfBeaches) {
        this.numOfBeaches = numOfBeaches;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.BEACH_N_SIGHTSEE_N_SKI_RESORT;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", numOfBeaches=" + numOfBeaches +
                ", numOfSights=" + numOfSights +
                ", numOfSkiResorts=" + numOfSkiResorts + '\'';
    }
}
