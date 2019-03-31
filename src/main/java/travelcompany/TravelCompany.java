package travelcompany;

import travelcompany.city.repo.impl.memory.CityArrayRepo;
import travelcompany.city.service.impl.CityDefaultService;
import travelcompany.country.domain.Country;
import travelcompany.country.repo.impl.memory.CountryArrayRepo;
import travelcompany.country.service.impl.CountryDefaultService;
import travelcompany.order.repo.impl.memory.OrderArrayRepo;
import travelcompany.storage.initor.StorageInitor;

import static travelcompany.storage.Storage.countriesArray;

public class TravelCompany {
    public static void main(String[] args) throws Exception {
        StorageInitor storageInitor = new StorageInitor(
                new CountryDefaultService(
                        new CountryArrayRepo(), new CityDefaultService(
                                new CityArrayRepo(), new OrderArrayRepo()), new OrderArrayRepo()));
        storageInitor.initStorageWithCountriesAndCities("src/main/resources/travelcompany/our-directions-part-1.xml", StorageInitor.DataSourceType.XML_FILE);
        for (Country country : countriesArray) {
            System.out.println(country.getAsStrWithoutCities());
        }
    }
}
