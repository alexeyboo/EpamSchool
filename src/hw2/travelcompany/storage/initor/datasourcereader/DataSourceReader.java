package hw2.travelcompany.storage.initor.datasourcereader;

public interface DataSourceReader <EXTRACTED_DATA>{
    EXTRACTED_DATA getDataFromFile(String file) throws Exception;
}
