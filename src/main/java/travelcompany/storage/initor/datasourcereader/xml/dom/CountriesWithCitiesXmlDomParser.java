package travelcompany.storage.initor.datasourcereader.xml.dom;

<<<<<<< HEAD
import travelcompany.city.domain.*;
import travelcompany.city.domain.impl.*;
import travelcompany.city.domain.typesofcities.*;
import travelcompany.country.domain.Country;
import travelcompany.storage.initor.datasourcereader.FileParser;
import travelcompany.storage.initor.exception.checked.InvalidCityDiscriminatorException;
=======
import hw2.travelcompany.city.domain.*;
import hw2.travelcompany.city.domain.impl.*;
import hw2.travelcompany.city.domain.typesofcities.*;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.storage.initor.datasourcereader.FileParser;
import hw2.travelcompany.storage.initor.exception.checked.InvalidCityDiscriminatorException;
>>>>>>> github/master
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import static travelcompany.common.solutions.xml.dom.XmlDomUtils.getOnlyElementTextContent;
import static travelcompany.storage.initor.exception.InitDataExceptionMeta.PARSE_CITY_DISCRIMINATOR_ERROR;
=======
import static hw2.travelcompany.common.solutions.xml.dom.XmlDomUtils.getOnlyElementTextContent;
import static hw2.travelcompany.storage.initor.exception.InitDataExceptionMeta.PARSE_CITY_DISCRIMINATOR_ERROR;
>>>>>>> github/master

public class CountriesWithCitiesXmlDomParser implements FileParser<List<Country>> {

    @Override
    public List<Country> parseFile(String file) throws Exception {

        if (!new File(file).exists() || new File(file).isDirectory()) {
            throw new Exception("not such file");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(file));

        Node root = doc.getElementsByTagName("countries").item(0);
        NodeList xmlCountries = ((Element) root).getElementsByTagName("country");

        List<Country> parsedCountries = new ArrayList<>();

        if (xmlCountries.getLength() > 0) {
            for (int i = 0; i < xmlCountries.getLength(); i++) {

                Node xmlCountry = xmlCountries.item(i);
                Country country = new Country();
                parsedCountries.add(country);

                NodeList countryInnerTags = xmlCountry.getChildNodes();

                for (int j = 0; j < countryInnerTags.getLength(); j++) {
                    Node countryInner = countryInnerTags.item(j);

                    switch (countryInner.getNodeName()) {
                        case "name": {
                            country.setName(countryInner.getTextContent());
                            break;
                        }
                        case "language": {
                            country.setLanguage(countryInner.getTextContent());
                            break;
                        }
                        case "cities": {
                            country.setCities(new ArrayList<>());
                            NodeList xmlCities = ((Element) countryInner).getElementsByTagName("cities");

                            for (int k = 0; k < xmlCities.getLength(); k++) {
                                Element xmlCity = (Element) xmlCities.item(i);
                                String type = xmlCity.getAttribute("type");
                                City city = null;
                                if (CityDiscriminator.isDisciminatorExist(type)) {
                                    switch (xmlCity.getAttribute("type")) {
                                        case "BEACH": {
                                            city = new BeachCity();
                                            setBeaches(city, xmlCity);
                                            break;
                                        }
                                        case "SKI_RESORT": {
                                            city = new SkiResortCity();
                                            setSkiResorts(city, xmlCity);
                                            break;
                                        }
                                        case "SIGHTSEE": {
                                            city = new SightseeCity();
                                            setSights(city, xmlCity);
                                            break;
                                        }
                                        case "BEACH_N_SKI_RESORT": {
                                            city = new BeachAndSkiResortCity();
                                            setBeaches(city, xmlCity);
                                            setSkiResorts(city, xmlCity);
                                            break;
                                        }
                                        case "BEACH_N_SIGHTSEE": {
                                            city = new BeachAndSightseeCity();
                                            setBeaches(city, xmlCity);
                                            setSights(city, xmlCity);
                                            break;
                                        }
                                        case "SIGHTSEE_N_SKI_RESORT": {
                                            city = new SightseeAndSkiResortCity();
                                            setSights(city, xmlCity);
                                            setSkiResorts(city, xmlCity);
                                            break;
                                        }
                                        case "BEACH_N_SKI_RESORT_N_SIGHTSEE": {
                                            city = new BeachAndSightseeAndSkiResortCity();
                                            setBeaches(city, xmlCity);
                                            setSights(city, xmlCity);
                                            setSkiResorts(city, xmlCity);
                                            break;
                                        }
                                    }
                                    city.setCountry(country);
                                    city.setName(getOnlyElementTextContent(xmlCity, "name"));
                                    city.setIsCapital(Boolean.parseBoolean(getOnlyElementTextContent(xmlCity, "getIsCapital")));
                                    city.setClimate(Climate.valueOf(getOnlyElementTextContent(xmlCity, "climate")));
                                    city.setPopulation(Integer.parseInt(getOnlyElementTextContent(xmlCity, "population")));
                                } else {
                                    throw new InvalidCityDiscriminatorException(PARSE_CITY_DISCRIMINATOR_ERROR.getCode(), PARSE_CITY_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(type));
                                }
                            }
                        }
                    }
                }
            }
        }
        return parsedCountries;
    }

    private void setBeaches(City city, Element xmlCity) {
        ((Beachable) city).setNumOfBeaches(Integer.parseInt(getOnlyElementTextContent(xmlCity, "numOfBeaches")));
    }

    private void setSights(City city, Element xmlCity) {
        ((Sightseeable) city).setNumOfSights(Integer.parseInt(getOnlyElementTextContent(xmlCity, "numOfSights")));
    }

    private void setSkiResorts(City city, Element xmlCity) {
        ((SkiResortable) city).setNumOfSkiResorts(Integer.parseInt(getOnlyElementTextContent(xmlCity, "numOfSkiResorts"));
    }
}

