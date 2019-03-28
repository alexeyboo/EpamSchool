//package travelcompany.storage.initor.datasourcereader;
//
//import travelcompany.city.domain.*;
//import travelcompany.country.domain.Country;
//import travelcompany.storage.initor.exception.checked.InvalidCityDiscriminatorException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import static travelcompany.storage.initor.exception.InitDataExceptionMeta.PARSE_CITY_DISCRIMINATOR_ERROR;
//
//public class DataSourceIoTxtFileFromResourcesReader implements FileParser <List<Country>> {
//
//    private static final String COUNTRY_PLACEHOLDER = "Country:";
//
//    @Override
//    public List<Country> parseFile(String file) throws Exception {
//        List<String> fileAsList = readFileToList(file);
//
//        List<Country> result = new ArrayList<>();
//        if (!fileAsList.isEmpty()) {
//            List<List<String>> countriesWithCities = splitFileToSeparateCountriesWithCities(fileAsList);
//
//            for (List<String> countryWithCities : countriesWithCities) {
//                result.add(parseCountry(countryWithCities));
//            }
//        }
//        return result;
//    }
//
//    private Country parseCountry(List<String> countryWithCities) throws InvalidCityDiscriminatorException {
//        String countryAsStr = countryWithCities.get(0).replaceAll(COUNTRY_PLACEHOLDER, "");
//        countryWithCities.remove(0);
//
//        String[] citiesCsv = countryWithCities.toArray(new String[0]);
//        return getCountry(countryAsStr, citiesCsv);
//    }
//
//    private Country getCountry(String countryCsv, String[] citiesCsv) throws InvalidCityDiscriminatorException {
//        String[] attrs = countryCsv.split("\\|");
//        int attrIndex = 0;
//
//        Country country = new Country(attrs[++attrIndex].trim(), attrs[++attrIndex].trim());
//        country.setCities(new ArrayList<>());
//
//        for (int i = 0; i < citiesCsv.length; i++) {
//            String csvCity = citiesCsv[i];
//            attrIndex = 0;
//            attrs = csvCity.split("\\|");
//
//            String disciminatorAsStr = attrs[attrIndex++].trim();
//            City city = createCityByDiscriminator(disciminatorAsStr);
//            city.setFirstName(attrs[attrIndex++].trim());
//            city.setIsCapital(Boolean.parseBoolean(attrs[attrIndex++].trim()));
//            city.setClimate(Climate.valueOf(attrs[attrIndex++].trim()));
//            city.setCountry(country);
//
//            if (TypeOneCity.class.getClass().equals(city.getClass())) {
//                appendTypeOneCityAttributes((TypeOneCity) city, attrs, attrIndex);
//            } else if (TypeTwoCity.class.getClass().equals(city.getClass())) {
//                appendTypeTwoCityAttributes((TypeTwoCity) city, attrs, attrIndex);
//            }
//
//            country.getCities().add(city);
//        }
//        return country;
//    }
//
//    private void appendTypeOneCityAttributes(TypeOneCity city, String[] attrs, int attrIndex) {
//        city.setCityTypeOneProperty(attrs[attrIndex++].trim());
//    }
//    private void appendTypeTwoCityAttributes(TypeTwoCity city, String[] attrs, int attrIndex) {
//        city.setCityTypeTwoProperty(attrs[attrIndex++].trim());
//    }
//
//    private City createCityByDiscriminator(String disciminatorAsStr) throws InvalidCityDiscriminatorException {
//        if (CityDiscriminator.isDisciminatorNotExist(disciminatorAsStr)) {
//            throw new InvalidCityDiscriminatorException(
//                    PARSE_CITY_DISCRIMINATOR_ERROR.getCode(),
//                    PARSE_CITY_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(disciminatorAsStr)
//            );
//        } else {
//            CityDiscriminator discriminator = CityDiscriminator.getDiscriminatorByName(disciminatorAsStr);
//            if (CityDiscriminator.TYPE_ONE.equals(discriminator)) {
//                return new TypeOneCity();
//            } else
//                return new TypeTwoCity();
//        }
//    }
//
//    private List<List<String>> splitFileToSeparateCountriesWithCities(List<String> fileAsList) {
//        List<List<String>> countriesWithCities = new ArrayList<>();
//
//        List<String> singleCountryWithCities = null;
//        for (String dataFromFile : fileAsList) {
//            if (!dataFromFile.isEmpty()) {
//                if (dataFromFile.contains(COUNTRY_PLACEHOLDER)) {
//                    if (singleCountryWithCities != null && !singleCountryWithCities.isEmpty()) {
//                        countriesWithCities.add(singleCountryWithCities);
//                    }
//                    singleCountryWithCities = new ArrayList<>();
//                    singleCountryWithCities.add(dataFromFile);
//                } else if (singleCountryWithCities != null) {
//                    singleCountryWithCities.add(dataFromFile);
//                }
//            }
//        }
//        return countriesWithCities;
//    }
//
//    private List<String> readFileToList(String file) throws IOException {
//        List<String> fileAsList = new ArrayList<>();
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            fileAsList.add(line);
//        }
//
//        return fileAsList;
//    }
//}
