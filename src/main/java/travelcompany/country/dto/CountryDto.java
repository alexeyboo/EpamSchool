package travelcompany.country.dto;


import travelcompany.city.dto.CityDto;
import travelcompany.common.business.dto.BaseDto;

import java.util.List;

public class CountryDto extends BaseDto<Long> {
    private String name;
    private String language;
    private List<CityDto> cities;

    public CountryDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<CityDto> getCities() {
        return cities;
    }

    public void setCities(List<CityDto> cities) {
        this.cities = cities;
    }
}
