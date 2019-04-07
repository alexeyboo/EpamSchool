package travelcompany.storage.initor.datasourcereader.xml.dom;

import travelcompany.city.domain.*;
import travelcompany.city.domain.impl.*;
import travelcompany.city.domain.typesofcities.*;
import travelcompany.country.domain.Country;
import travelcompany.common.solutions.parser.FileParser;
import travelcompany.storage.initor.exception.checked.InvalidCityDiscriminatorException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;


import static travelcompany.common.solutions.xml.dom.XmlDomUtils.*;
import static travelcompany.storage.initor.exception.InitDataExceptionMeta.PARSE_CITY_DISCRIMINATOR_ERROR;

public class CountriesWithCitiesXmlDomParser implements FileParser<List<Country>> {

    @Override
    public List<Country> parseFile(String file) throws Exception {

        Document doc = getDocument(file);
        Element root = getOnlyElement(doc, "countries");

        NodeList xmlCountries = root.getElementsByTagName("country");
        List<Country> parsedCountries = new ArrayList<>();

        if (xmlCountries.getLength() > 0) {
            for (int i = 0; i < xmlCountries.getLength(); i++) {                
                parsedCountries.add(getCountryFromXmlElement(xmlCountries.item(i)));
            }
        }
        return parsedCountries;
    }

    private Country getCountryFromXmlElement(Node xmlCountry) throws Exception {
        Country country = new Country();

        country.setName(getOnlyElementTextContent((Element) xmlCountry, "name"));
        country.setLanguage(getOnlyElementTextContent((Element) xmlCountry, "language"));
        NodeList cities = ((Element) xmlCountry).getElementsByTagName("cities");
        if (cities.getLength() > 0) {
            country.setCities(new ArrayList<>());

            for (int i = 0; i < cities.getLength(); i++) {
                City city = getCityFromXmlDocument((Element) cities.item(i));
                country.getCities().add(city);
            }
        }

        return country;
    }

    private City getCityFromXmlDocument(Element xmlCity) throws Exception {
        return CityDiscriminator.getDiscriminatorByName(xmlCity.getAttribute("type"))
                .map(cityDiscriminator -> createCityDiscriminatorAndXmlElement(cityDiscriminator, xmlCity))
                .orElseThrow(() -> new InvalidCityDiscriminatorException(PARSE_CITY_DISCRIMINATOR_ERROR.getCode(),
                        PARSE_CITY_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(xmlCity.getAttribute("type"))));
    }

    private City createCityDiscriminatorAndXmlElement(CityDiscriminator cityDiscriminator, Element xmlCity) {
        City city = null;

        switch (cityDiscriminator) {
            case BEACH: {
                city = new BeachCity();
                setBeaches(city, xmlCity);
                break;
            }
            case SKI_RESORT: {
                city = new SkiResortCity();
                setSkiResorts(city, xmlCity);
                break;
            }
            case SIGHTSEE: {
                city = new SightseeCity();
                setSights(city, xmlCity);
                break;
            }
            case BEACH_N_SKI_RESORT: {
                city = new BeachAndSkiResortCity();
                setBeaches(city, xmlCity);
                setSkiResorts(city, xmlCity);
                break;
            }
            case BEACH_N_SIGHTSEE: {
                city = new BeachAndSightseeCity();
                setBeaches(city, xmlCity);
                setSights(city, xmlCity);
                break;
            }
            case SIGHTSEE_N_SKI_RESORT: {
                city = new SightseeAndSkiResortCity();
                setSights(city, xmlCity);
                setSkiResorts(city, xmlCity);
                break;
            }
            case BEACH_N_SIGHTSEE_N_SKI_RESORT: {
                city = new BeachAndSightseeAndSkiResortCity();
                setBeaches(city, xmlCity);
                setSights(city, xmlCity);
                setSkiResorts(city, xmlCity);
                break;
            }
        }
        fillCommonCityData(city, xmlCity);
        
        return city;
    }

    private void fillCommonCityData(City city, Element xmlCity) {
        city.setName(getOnlyElementTextContent(xmlCity, "name"));
        city.setIsCapital(Boolean.parseBoolean(getOnlyElementTextContent(xmlCity, "isCapital")));
        city.setClimate(Climate.valueOf(getOnlyElementTextContent(xmlCity, "climate")));
        city.setPopulation(Integer.parseInt(getOnlyElementTextContent(xmlCity, "population")));
    }

    private void setBeaches(City city, Element xmlCity) {
        ((Beachable) city).setNumOfBeaches(Integer.parseInt(getOnlyElementTextContent(xmlCity, "numOfBeaches")));
    }

    private void setSights(City city, Element xmlCity) {
        ((Sightseeable) city).setNumOfSights(Integer.parseInt(getOnlyElementTextContent(xmlCity, "numOfSights")));
    }

    private void setSkiResorts(City city, Element xmlCity) {
        ((SkiResortable) city).setNumOfSkiResorts(Integer.parseInt(getOnlyElementTextContent(xmlCity, "numOfSkiResorts")));
    }
}

