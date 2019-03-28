package travelcompany;

import travelcompany.city.repo.impl.CityMemoryArrayRepo;
import travelcompany.city.service.impl.CityDefaultService;
import travelcompany.country.domain.Country;
import travelcompany.country.repo.impl.CountryMemoryArrayRepo;
import travelcompany.country.service.impl.CountryDefaultService;
import travelcompany.order.repo.impl.OrderMemoryArrayRepo;
import travelcompany.storage.initor.StorageInitor;

import static travelcompany.storage.Storage.countriesArray;

public class TravelCompany {
    public static void main(String[] args) throws Exception {
        StorageInitor storageInitor = new StorageInitor(
                new CountryDefaultService(
                        new CountryMemoryArrayRepo(), new CityDefaultService(
                                new CityMemoryArrayRepo(), new OrderMemoryArrayRepo()), new OrderMemoryArrayRepo()));
        storageInitor.initStorageWithCountriesAndCities("src/main/resources/travelcompany/our-directions.xml", StorageInitor.DataSourceType.XML_FILE);
        for (Country country : countriesArray) {
            System.out.println(country);
        }
    }
}
