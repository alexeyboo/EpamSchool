package travelcompany.storage.initor;

import travelcompany.country.domain.Country;
import travelcompany.country.service.CountryService;
import travelcompany.common.solutions.parser.FileParser;
import travelcompany.storage.initor.datasourcereader.xml.sax.CountriesWithCitiesXmlSaxParser;
import travelcompany.storage.initor.exception.checked.CountryCityParseXmlFileException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static travelcompany.storage.initor.exception.InitDataExceptionMeta.PARSE_COUNTRY_CITY_ERROR;

public class StorageInitor {
    private CountryService countryService;

    public StorageInitor(CountryService countryService) {
        this.countryService = countryService;
    }

    public enum DataSourceType {
        TXT_FILE, XML_FILE
    }

    public void initStorageWithCountriesAndCities(List<File> files, DataSourceType dataSourceType) throws Exception {
        List<CountryCityFileParser> countryCityFileParsers = prepareAsyncParsers(files, dataSourceType);
        List<Country> countriesToPersist = asyncParseFilesAndWaitForResult(countryCityFileParsers);

        if (!countriesToPersist.isEmpty()) {
            countryService.insert(countriesToPersist);
        }
    }

    private List<Country> asyncParseFilesAndWaitForResult(List<CountryCityFileParser> countryCityFileParsers) throws Exception {
        for (CountryCityFileParser countryCityFileParser : countryCityFileParsers) {
            countryCityFileParser.asyncParseCountries();
        }

        List<Country> countriesToPersist = new ArrayList<>();
        for (CountryCityFileParser countryCityFileParser : countryCityFileParsers) {
            countryCityFileParser.blockUntilWorkIsFinished();
            if (countryCityFileParser.getParseException() != null) {
                throw new CountryCityParseXmlFileException(
                        PARSE_COUNTRY_CITY_ERROR.getCode(),
                        PARSE_COUNTRY_CITY_ERROR.getDescription(),
                        countryCityFileParser.getParseException());
            }
            countriesToPersist.addAll(countryCityFileParser.getCountries());
        }

        return countriesToPersist;
    }

    private List<CountryCityFileParser> prepareAsyncParsers(List<File> files, DataSourceType dataSourceType) {
        List<CountryCityFileParser> countryCityFileParsers = new ArrayList<>();
        
        for (File file : files) {
            countryCityFileParsers.add(new CountryCityFileParser(dataSourceType, file));
        }
        
        return countryCityFileParsers;
    }

    private List<Country> getCountriesFromStorage(String filePath, DataSourceType dataSourceType) throws Exception {
        FileParser<List<Country>> dataSourceReader = null;

        switch (dataSourceType) {
            case TXT_FILE: {
//                dataSourceReader = new DataSourceIoTxtFileFromResourcesReader();
                break;
            }
            case XML_FILE: {
                dataSourceReader = new CountriesWithCitiesXmlSaxParser();
//                dataSourceReader = new CountriesWithCitiesXmlDomParser();
//                dataSourceReader = new CountriesWithCitiesXmlStaxParser();
                break;
            }            
        }

        return dataSourceReader.parseFile(filePath);
    }
}
