package travelcompany.storage.initor.datasourcereader.xml.stax;

import travelcompany.city.domain.City;
import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.domain.Climate;
import travelcompany.city.domain.impl.*;
import travelcompany.city.domain.typesofcities.Beachable;
import travelcompany.city.domain.typesofcities.Sightseeable;
import travelcompany.city.domain.typesofcities.SkiResortable;
import travelcompany.common.solutions.xml.stax.parse.CustomStaxReader;
import travelcompany.country.domain.Country;
import travelcompany.common.solutions.parser.FileParser;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

import static travelcompany.common.solutions.xml.stax.XmlStaxUtils.readContent;

public class CountriesWithCitiesXmlStaxParser implements FileParser<List<Country>> {
    private final RuntimeException NO_END_TAG_FOUND_EXCEPTION = new RuntimeException("Suitable end tag NOT found");

    @Override
    public List<Country> parseFile(String file) throws Exception {

        List<Country> result = new ArrayList<>();

        try (CustomStaxReader staxReader = CustomStaxReader.newInstance(file)) {
            XMLStreamReader reader = staxReader.getReader();

            while (reader.hasNext()) {
                int eventType = reader.next();

                switch (eventType) {
                    case XMLStreamReader.START_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("countries".equals(tagName)) {
                            result = new ArrayList<>(readCountries(reader));
                        }
                        break;
                    }
                    case XMLStreamReader.END_ELEMENT: {
                        return result;
                    }
                }
            }
        }

        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private List<Country> readCountries(XMLStreamReader reader) throws XMLStreamException {
        List<Country> countries = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("countries".equals(tagName)) {
                        countries.add(readCountry(reader));
                    }
                    break;
                }
                case XMLStreamReader.END_ELEMENT: {
                    return countries;
                }
            }
        }

        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private Country readCountry(XMLStreamReader reader) throws XMLStreamException {
        Country country = new Country();
        while (reader.hasNext()){
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("name".equals(tagName)) {
                        country.setName(readContent(reader));
                    } else if ("language".equals(tagName)) {
                        country.setLanguage(readContent(reader));
                    } else if ("cities".equals(tagName)) {
                        country.setCities(readCities(reader));
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    return country;
                }
            }
        }
        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private List<City> readCities(XMLStreamReader reader) throws XMLStreamException {
        List<City> cities = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    if ("city".equals(reader.getLocalName())) {
                        cities.add(readCity(reader));
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    return cities;
                }
            }
        }

        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private City readCity(XMLStreamReader reader) throws XMLStreamException {        
        String type = reader.getAttributeValue(null, "type");
        City city = createCity(type);
        
        while (reader.hasNext()) {
            int eventType = reader.next();
            
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    appendCommonCityData(city, tagName, reader);
                    if (city instanceof Beachable) {
                        appendBeachableAttributes((Beachable) city, tagName, reader);
                    }
                    if (city instanceof Sightseeable) {
                        appendSightseeableAttributes((Sightseeable) city, tagName, reader);
                    }
                    if (city instanceof SkiResortable) {
                        appendSkiResortableAttributes((SkiResortable) city, tagName, reader);
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    return city;
                }
            }
        }
        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private void appendSkiResortableAttributes(SkiResortable city, String tagName, XMLStreamReader reader) throws XMLStreamException {
        if ("numOfSkiResorts".equals(tagName)) {
            city.setNumOfSkiResorts(Integer.parseInt(readContent(reader)));
        }
    }

    private void appendSightseeableAttributes(Sightseeable city, String tagName, XMLStreamReader reader) throws XMLStreamException {
        if ("numOfSights".equals(tagName)) {
            city.setNumOfSights(Integer.parseInt(readContent(reader)));
        }
    }

    private void appendBeachableAttributes(Beachable city, String tagName, XMLStreamReader reader) throws XMLStreamException {
        if ("numOfBeaches".equals(tagName)) {
            city.setNumOfBeaches(Integer.parseInt(readContent(reader)));
        }
    }

    private void appendCommonCityData(City city, String tagName, XMLStreamReader reader) throws XMLStreamException {
        if ("name".equals(tagName)) {
            city.setName(readContent(reader));
        } else if ("population".equals(tagName)) {
            city.setPopulation(Integer.parseInt(readContent(reader)));
        } else if ("isCapital".equals(tagName)) {
            city.setIsCapital(Boolean.parseBoolean(readContent(reader)));
        } else if ("climate".equals(tagName)) {
            city.setClimate(Climate.valueOf(readContent(reader)));
        }
    }

    private City createCity(String type) {
        CityDiscriminator cityDiscriminator = CityDiscriminator.valueOf(type);

        switch (cityDiscriminator) {
            case SIGHTSEE_N_SKI_RESORT:
                return new SightseeAndSkiResortCity();
            case BEACH_N_SKI_RESORT:
                return new BeachAndSkiResortCity();
            case BEACH_N_SIGHTSEE:
                return new BeachAndSightseeCity();
            case BEACH:
                return new BeachCity();
            case SIGHTSEE:
                return new SightseeCity();
            case SKI_RESORT:
                return new SkiResortCity();
            case BEACH_N_SIGHTSEE_N_SKI_RESORT:
                return new BeachAndSightseeAndSkiResortCity();
        }

        return null;
    }
}
