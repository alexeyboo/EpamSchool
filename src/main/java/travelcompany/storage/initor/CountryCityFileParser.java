package travelcompany.storage.initor;

import travelcompany.country.domain.Country;
import travelcompany.common.solutions.parser.FileParser;
import travelcompany.storage.initor.datasourcereader.xml.sax.CountriesWithCitiesXmlSaxParser;

import java.io.File;
import java.util.List;

public class CountryCityFileParser implements Runnable {
    private StorageInitor.DataSourceType dataSourceType;
    private List<Country> countries;
    private Thread thread;
    private File fileToParse;
    private volatile Exception parseException;

    public CountryCityFileParser(StorageInitor.DataSourceType dataSourceType, File fileToParse) {
        this.dataSourceType = dataSourceType;
        this.fileToParse = fileToParse;
        thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            countries = getCountriesFromStorage(fileToParse.getAbsolutePath(), dataSourceType);
        } catch (Exception e) {
            System.out.println("Error while parse file with countries");
            parseException = e;
        }
    }

    public synchronized List<Country> getCountries() {
        return countries;
    }

    public void asyncParseCountries() {
        thread.start();
    }

    public void blockUntilWorkIsFinished() throws InterruptedException {
        thread.join();
    }

    private List<Country> getCountriesFromStorage(String absolutePath, StorageInitor.DataSourceType dataSourceType) throws Exception {
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

        return dataSourceReader.parseFile(absolutePath);
    }

    public Exception getParseException() {
        return parseException;
    }
}
