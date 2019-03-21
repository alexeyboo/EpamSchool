package hw2.travelcompany.storage.initor.datasourcereader;

import hw2.travelcompany.common.solutions.xml.sax.XmlSaxUtils;
import hw2.travelcompany.country.domain.Country;

import javax.xml.parsers.SAXParser;
import java.util.List;

public class CountriesWithCitiesXmlSaxParser implements FileParser <List<Country>> {
    @Override
    public List<Country> parseFile(String file) throws Exception {
        SAXParser saxParser = XmlSaxUtils.getParser();
        CountriesWithCitiesSaxHandler countriesWithCitiesSaxHandler = new CountriesWithCitiesXmlSaxParser();
    }
}
