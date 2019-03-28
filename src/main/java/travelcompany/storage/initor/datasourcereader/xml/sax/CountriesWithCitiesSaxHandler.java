package travelcompany.storage.initor.datasourcereader.xml.sax;

import travelcompany.city.domain.*;
import travelcompany.city.domain.impl.*;
import travelcompany.city.domain.typesofcities.*;
import travelcompany.country.domain.Country;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static travelcompany.common.solutions.utils.CollectionUtils.getLast;

public class CountriesWithCitiesSaxHandler extends DefaultHandler {
    private StringBuilder content = new StringBuilder();
    private List<Country> countries;
    private Country country;
    private List<City> cities;
    private City city;
    private boolean nameForCountryWasSet;

    List<String> path = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        path.add(qName);

        switch (qName) {
            case "countries": {
                countries = new ArrayList<>();
                break;
            }
            case "country": {
                countries.add(new Country());
                nameForCountryWasSet = false;
                break;
            }
            case "cities": {
                cities = new ArrayList<>();
                break;
            }
            case "city": {
                switch (attributes.getValue("type")) {
                    case "BEACH":{
                        city = new BeachCity();
                        break;
                    }
                    case "SKI_RESORT":{
                        city = new SkiResortCity();
                        break;
                    }
                    case "SIGHTSEE":{
                        city = new SightseeCity();
                        break;
                    }
                    case "BEACH_N_SKI_RESORT":{
                        city = new BeachAndSkiResortCity();
                        break;
                    }
                    case "BEACH_N_SIGHTSEE":{
                        city = new BeachAndSightseeCity();
                        break;
                    }
                    case "SIGHTSEE_N_SKI_RESORT":{
                        city = new SightseeAndSkiResortCity();
                        break;
                    }
                    case "BEACH_N_SKI_RESORT_N_SIGHTSEE":{
                        city = new BeachAndSightseeAndSkiResortCity();
                        break;
                    }
                }
                city.setCountry(getLast(countries));
                cities.add(city);
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "name": {
                if (!nameForCountryWasSet) {
                    getLast(countries).setName(content.toString());
                    nameForCountryWasSet = true;
                } else {
                    getLast(cities).setName(content.toString());
                }
                content.setLength(0);
                break;
            }
            case "language": {
                getLast(countries).setLanguage(content.toString());
                content.setLength(0);
                break;
            }
            case "population": {
                getLast(cities).setPopulation(Integer.parseInt(content.toString()));
                content.setLength(0);
                break;
            }
            case "getIsCapital": {
                getLast(cities).setIsCapital(Boolean.parseBoolean(content.toString()));
                content.setLength(0);
                break;
            }
            case "climate": {
                getLast(cities).setClimate(Climate.valueOf(content.toString()));
                content.setLength(0);
                break;
            }
            case "numOfBeaches": {
                getLastBeachable().setNumOfBeaches(Integer.parseInt(content.toString()));
                content.setLength(0);
                break;
            }
            case "numOfSkiResorts": {
                getLastSkiResortable().setNumOfSkiResorts(Integer.parseInt(content.toString()));
                content.setLength(0);
                break;
            }
            case "numOfSights": {
                getLastSightseeable().setNumOfSights(Integer.parseInt(content.toString()));
                content.setLength(0);
                break;
            }

        }
    }

    private Beachable getLastBeachable() {
        return (Beachable) getLast(cities);
    }
    private SkiResortable getLastSkiResortable() {
        return (SkiResortable) getLast(cities);
    }
    private Sightseeable getLastSightseeable() {
        return (Sightseeable) getLast(cities);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        content.append(data.trim());
    }

    public List<Country> getCountries() {
        return countries;
    }
}
