package travelcompany.storage.initor.datasourcereader.xml.sax;

import hw2.travelcompany.common.solutions.xml.sax.XmlSaxUtils;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.storage.initor.datasourcereader.FileParser;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.util.List;

public class CountriesWithCitiesXmlSaxParser implements FileParser<List<Country>> {
    @Override
    public List<Country> parseFile(String file) throws Exception {
        SAXParser saxParser = XmlSaxUtils.getParser();
        CountriesWithCitiesSaxHandler saxHandler = new CountriesWithCitiesSaxHandler();
        saxParser.parse(new File(file), saxHandler);
        return saxHandler.getCountries();
    }
}
