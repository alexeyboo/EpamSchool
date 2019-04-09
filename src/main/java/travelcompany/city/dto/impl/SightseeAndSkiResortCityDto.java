package travelcompany.city.dto.impl;

import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.dto.CityDto;
import travelcompany.city.dto.typesofcities.SightseeableDto;
import travelcompany.city.dto.typesofcities.SkiResortableDto;

public class SightseeAndSkiResortCityDto extends CityDto implements SightseeableDto, SkiResortableDto {
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

    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.SIGHTSEE_N_SKI_RESORT;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", numOfSights=" + numOfSights +
                ", numOfSkiResorts=" + numOfSkiResorts + '\'';
    }
}
