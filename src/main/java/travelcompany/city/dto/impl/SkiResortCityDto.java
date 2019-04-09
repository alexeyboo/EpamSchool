package travelcompany.city.dto.impl;

import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.dto.CityDto;
import travelcompany.city.dto.typesofcities.SkiResortableDto;

public class SkiResortCityDto extends CityDto implements SkiResortableDto {
    private int numOfSkiResorts;

    public int getNumOfSkiResorts() {
        return numOfSkiResorts;
    }

    public void setNumOfSkiResorts(int numOfSkiResorts) {
        this.numOfSkiResorts = numOfSkiResorts;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.SKI_RESORT;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", numOfSkiResorts=" + numOfSkiResorts + '\'';
    }
}
