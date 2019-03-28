<<<<<<< HEAD
package hw2.travelcompany.storage.initor;

import travelcompany.country.domain.Country;
import travelcompany.country.service.CountryService;
import travelcompany.storage.initor.datasourcereader.FileParser;
import travelcompany.storage.initor.datasourcereader.xml.stax.CountriesWithCitiesXmlStaxParser;
=======
package travelcompany.storage.initor;

import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.service.CountryService;
import hw2.travelcompany.storage.initor.datasourcereader.xml.dom.CountriesWithCitiesXmlDomParser;
import hw2.travelcompany.storage.initor.datasourcereader.xml.sax.CountriesWithCitiesXmlSaxParser;
//import hw2.travelcompany.storage.initor.datasourcereader.DataSourceIoTxtFileFromResourcesReader;
import hw2.travelcompany.storage.initor.datasourcereader.FileParser;
>>>>>>> github/master

import java.util.List;

public class StorageInitor {
    private CountryService countryService;

    public StorageInitor(CountryService countryService) {
        this.countryService = countryService;
    }

    public enum DataSourceType {
        TXT_FILE, XML_FILE, JSON_FILE;
    }

    public void initStorageWithCountriesAndCities(String filePath, DataSourceType dataSourceType) throws Exception {
        List<Country> countriesToPersist = getCountriesFromStorage(filePath, dataSourceType);
        
        if (!countriesToPersist.isEmpty()) {
            for (Country country : countriesToPersist) {
                countryService.insert(country);
            }
        }
    }

    private List<Country> getCountriesFromStorage(String filePath, DataSourceType dataSourceType) throws Exception {

        FileParser<List<Country>> dataSourceReader = null;

        switch (dataSourceType) {
            case TXT_FILE: {
//                dataSourceReader = new DataSourceIoTxtFileFromResourcesReader();
                break;
            }
            case XML_FILE: {
<<<<<<< HEAD
                //dataSourceReader = new CountriesWithCitiesXmlSaxParser();
                //dataSourceReader = new CountriesWithCitiesXmlDomParser();
                dataSourceReader = new CountriesWithCitiesXmlStaxParser();
=======
                dataSourceReader = new CountriesWithCitiesXmlSaxParser();
                //dataSourceReader = new CountriesWithCitiesXmlDomParser();
>>>>>>> github/master
                break;
            }
            case JSON_FILE: {
                break;
            }
        }

        return dataSourceReader.parseFile(filePath);
    }
}
