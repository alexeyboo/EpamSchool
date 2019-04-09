package travelcompany.city.dto;

import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.domain.Climate;
import travelcompany.common.business.dto.BaseDto;

public abstract class CityDto extends BaseDto<Long> {
    protected String name;
    protected int population;
    protected boolean isCapital;
    protected Climate climate;
    protected CityDiscriminator discriminator;

    public CityDto() {
        initDiscriminator();
    }

    protected abstract void initDiscriminator();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public CityDiscriminator getDiscriminator() {
        return discriminator;
    }
}
