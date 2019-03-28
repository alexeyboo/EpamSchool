package travelcompany;

import hw2.travelcompany.city.repo.impl.CityMemoryArrayRepo;
import hw2.travelcompany.country.repo.impl.CountryMemoryArrayRepo;
import hw2.travelcompany.country.service.impl.CountryDefaultService;
import hw2.travelcompany.storage.initor.StorageInitor;

import static hw2.travelcompany.storage.Storage.countriesList;

public class TravelCompany {
    public static void main(String[] args) throws Exception {
        StorageInitor storageInitor = new StorageInitor(new CountryDefaultService(new CountryMemoryArrayRepo(), new CityMemoryArrayRepo()));
        storageInitor.initStorageWithCountriesAndCities("resources/hw2/travelcompany/our-directions.xml", StorageInitor.DataSourceType.XML_FILE);
        System.out.println((countriesList));
    }
}
