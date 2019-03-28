package hw2.travelcompany.storage.initor;

import travelcompany.country.domain.Country;
import travelcompany.country.service.CountryService;
import travelcompany.storage.initor.datasourcereader.FileParser;
import travelcompany.storage.initor.datasourcereader.xml.stax.CountriesWithCitiesXmlStaxParser;

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
                //dataSourceReader = new CountriesWithCitiesXmlSaxParser();
                //dataSourceReader = new CountriesWithCitiesXmlDomParser();
                dataSourceReader = new CountriesWithCitiesXmlStaxParser();
                break;
            }
            case JSON_FILE: {
                break;
            }
        }

        return dataSourceReader.parseFile(filePath);
    }
}
