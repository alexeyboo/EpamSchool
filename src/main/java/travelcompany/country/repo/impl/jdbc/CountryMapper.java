package travelcompany.country.repo.impl.jdbc;

import travelcompany.common.business.exception.jdbc.ResultSetMappingException;
import travelcompany.country.domain.Country;

import java.sql.ResultSet;

public final class CountryMapper {
    private static final String COUNTRY_CLASS_NAME = Country.class.getSimpleName();

    public CountryMapper() {
    }

    public static Country mapCountry(ResultSet rs) throws ResultSetMappingException {
        try {
            Country country = new Country();
            country.setId(rs.getLong("ID"));
            country.setName(rs.getString("NAME"));
            country.setLanguage(rs.getString("LANGUAGE"));

            return country;
        } catch (Exception e) {
            throw new ResultSetMappingException(COUNTRY_CLASS_NAME, e);
        }
    }
}
