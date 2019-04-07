package travelcompany.storage.initor.datasourcereader.xml.sax;


import travelcompany.city.domain.*;
import travelcompany.city.domain.impl.*;
import travelcompany.city.domain.typesofcities.*;
import travelcompany.country.domain.Country;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static travelcompany.common.solutions.utils.CollectionUtils.getLast;

public class CountriesWithCitiesSaxHandler extends DefaultHandler {
    private static final String OUR_DIRECTIONS_PATH = "our-directions";
    private static final String COUNTRIES_PATH = OUR_DIRECTIONS_PATH + "/countries";
    private static final String COUNTRY_PATH = COUNTRIES_PATH + "/country";
    private static final String COUNTRY_NAME_PATH = COUNTRY_PATH + "/name";
    private static final String COUNTRY_LANGUAGE_PATH = COUNTRY_PATH + "/language";
    private static final String CITIES_PATH = COUNTRY_PATH + "/cities";
    private static final String CITY_PATH = CITIES_PATH + "/city";
    private static final String CITY_NAME_PATH = CITY_PATH + "/name";
    private static final String CITY_POPULATION_PATH = CITY_PATH + "/population";
    private static final String CITY_IS_CAPITAL_PATH = CITY_PATH + "/isCapital";
    private static final String CITY_CLIMATE_PATH = CITY_PATH + "/climate";

    private static final String CITY_BEACHABLE_NUM_OF_BEACHES_PATH = CITY_PATH + "/numOfBeaches";

    private static final String CITY_SKI_RESORTABLE_NUM_OF_SKI_RESORTS_PATH = CITY_PATH + "/numOfSkiResorts";

    private static final String CITY_SIGHTSEEABLE_NUM_OF_SIGHTS_PATH = CITY_PATH + "/numOfSights";

    private StringBuilder content = new StringBuilder();
    private List<Country> countries = Collections.emptyList();
    private List<City> cities = Collections.emptyList();

    private Deque<String> tagStack = new ArrayDeque<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        content.setLength(0);
        tagStack.add(qName);

        switch (stackAsStringPath()) {
            case COUNTRIES_PATH: {
                countries = new ArrayList<>();
                break;
            }
            case COUNTRY_PATH: {
                countries.add(new Country());
                break;
            }
            case CITIES_PATH: {
                cities = new ArrayList<>();
                getLast(countries).setCities(cities);
                break;
            }
            case CITY_PATH: {
                switch (attributes.getValue("type")) {
                    case "BEACH": {
                        cities.add(new BeachCity());
                        break;
                    }
                    case "SKI_RESORT": {
                        cities.add(new SkiResortCity());
                        break;
                    }
                    case "SIGHTSEE": {
                        cities.add(new SightseeCity());
                        break;
                    }
                    case "BEACH_N_SKI_RESORT": {
                        cities.add(new BeachAndSkiResortCity());
                        break;
                    }
                    case "BEACH_N_SIGHTSEE": {
                        cities.add(new BeachAndSightseeCity());
                        break;
                    }
                    case "SIGHTSEE_N_SKI_RESORT": {
                        cities.add(new SightseeAndSkiResortCity());
                        break;
                    }
                    case "BEACH_N_SIGHTSEE_N_SKI_RESORT": {
                        cities.add(new BeachAndSightseeAndSkiResortCity());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String dataAsStr = content.toString();

        switch (stackAsStringPath()) {
            case COUNTRY_NAME_PATH: {
                getLast(cities).setName(dataAsStr);
                break;
            }
            case COUNTRY_LANGUAGE_PATH: {
                getLast(countries).setLanguage(dataAsStr);
                break;
            }
            case CITY_POPULATION_PATH: {
                getLast(cities).setPopulation(Integer.parseInt(dataAsStr));
                break;
            }
            case CITY_IS_CAPITAL_PATH: {
                getLast(cities).setIsCapital(Boolean.parseBoolean(dataAsStr));
                break;
            }
            case CITY_CLIMATE_PATH: {
                getLast(cities).setClimate(Climate.valueOf(dataAsStr));
                break;
            }
            case CITY_BEACHABLE_NUM_OF_BEACHES_PATH: {
                getLastBeachable().setNumOfBeaches(Integer.parseInt(dataAsStr));
                break;
            }
            case CITY_SKI_RESORTABLE_NUM_OF_SKI_RESORTS_PATH: {
                getLastSkiResortable().setNumOfSkiResorts(Integer.parseInt(dataAsStr));
                break;
            }
            case CITY_SIGHTSEEABLE_NUM_OF_SIGHTS_PATH: {
                getLastSightseeable().setNumOfSights(Integer.parseInt(dataAsStr));
                break;
            }
        }
        tagStack.removeLast();
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
        content.append(data.replaceAll("\\n", ""));
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        super.ignorableWhitespace(ch, start, length);
    }

    public List<Country> getCountries() {
        return countries;
    }

    private String stackAsStringPath() {
        StringBuilder fullPath = new StringBuilder();

        Iterator<String> iter = tagStack.iterator();
        while (iter.hasNext()) {
            String tag = iter.next();
            fullPath.append(tag);

            if (iter.hasNext()) {
                fullPath.append("/");
            }
        }

        return fullPath.toString();
    }
}
