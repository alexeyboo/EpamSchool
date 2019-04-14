package travelcompany.country.repo.impl.jdbc;

import travelcompany.City;
import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.domain.impl.BeachAndSightseeAndSkiResortCity;
import travelcompany.common.business.exception.jdbc.KeyGeneratorException;
import travelcompany.common.business.exception.jdbc.SqlException;
import travelcompany.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import travelcompany.common.business.search.SortDirection;
import travelcompany.common.business.search.SortType;
import travelcompany.common.solutions.repo.jdbc.PreparedStatementConsumer;
import travelcompany.common.solutions.repo.jdbc.QueryWrapper;
import travelcompany.country.domain.Country;
import travelcompany.country.repo.CountryRepo;
import travelcompany.country.search.CountrySearchCondition;

import java.nio.file.AtomicMoveNotSupportedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CountryDefaultRepo implements CountryRepo {
    private static final List<String> COMPLEX_ORDER_FIELDS = Arrays.asList("NAME", "LANGUAGE");

    @Override
    public List<? extends Country> search(CountrySearchCondition searchCondition) {
        try {
            SqlPreparedStatementConsumerHolder sqlParamHolder = getSearchSqlAndPrStmtHolder(searchCondition);

            return QueryWrapper.select(sqlParamHolder.getSql(),
                    CountryMapper::mapCountry,
                    ps -> {
                        List<PreparedStatementConsumer> psConsumers = sqlParamHolder.getPreparedStatementConsumers();
                        for (PreparedStatementConsumer psConsumer : psConsumers) {
                            psConsumer.consume(ps);
                        }
                    });
        } catch (Exception e) {
            throw new SqlException(e);
        }
    }

    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(CountrySearchCondition searchCondition) {
        String sql = "SELECT * FROM COUNTRY";

        List<PreparedStatementConsumer> psConsumers = new ArrayList<>();

        if (searchCondition.getId() != null) {
            sql += " WHERE ID = ?";
            psConsumers.add(ps -> ps.setLong(1, searchCondition.getId()));
        } else {
            AtomicInteger index = new AtomicInteger(0);
            List<String> where = new ArrayList<>();

            if (searchCondition.searchByName()) {
                where.add("(NAME = ?)");
                psConsumers.add(ps -> ps.setString(index.incrementAndGet(), searchCondition.getName()));
            }

            if (searchCondition.searchByLanguage()) {
                where.add("(LANGUAGE + )");
                psConsumers.add(ps -> ps.setString(index.incrementAndGet(), searchCondition.getLanguage()));
            }

            String whereStr = String.join(" AND ", where);
            //(NAME =?) AND (LANGUAGE = ?)
            //SELECT * FROM COUNTRY WHERE (NAME =?) AND (LANGUAGE = ?)
            sql += (whereStr.isEmpty() ? "" : " WHERE " + whereStr) + getSorting(searchCondition);

            if (searchCondition.shouldPaginate()) {
                sql += getPageableSqlPart(searchCondition);
            }
        }
        
        return new SqlPreparedStatementConsumerHolder(sql, psConsumers);
    }

    private String getSorting(CountrySearchCondition searchCondition) {
        if (searchCondition.needSorting()) {
            SortType sortType = searchCondition.getSortType();
            SortDirection sortDirection = searchCondition.getSortDirection();

            switch (sortType) {
                case SIMPLE: {
                    return " SORT BY " + searchCondition.getSortByField().name() + " " + sortDirection;
                }

                case COMPLEX: {
                    return " SORT BY " + String.join(", ", COMPLEX_ORDER_FIELDS) + " " + sortDirection;
                }
            }
        }

        return "";
    }

    private String getPageableSqlPart(CountrySearchCondition searchCondition) {
        return " LIMIT " + searchCondition.getPaginator().getLimit() + " OFFSET " + searchCondition.getPaginator().getOffset();
    }

    @Override
    public List<? extends Country> findAllCountriesFetchingModels() {
        try {
            String sql =
                    "SELECT \n" +
                            " cn.ID as COUNTRY_IDENT, \n" +
                            " ct.ID as CITY_IDENT, \n" +
                            " cn.NAME as COUNTRY_NAME, \n" +
                            " ct.NAME as CITY_NAME, \n" +
                            " cn.*, ct.* \n" +
                            " \n" +
                            " FROM COUNTRY cn \n" +
                            " LEFT JOIN CITY ct ON (cn.ID = ct.COUNTRY_ID)";
            return QueryWrapper.select(sql, (rs, accumulator) -> {
                Map<Long, Country> countryMap = new LinkedHashMap<>();

                while (rs.next()) {
                    long countryId = rs.getLong("COUNTRY_IDENT");

                    if (!countryMap.containsKey(countryId)) {
                        Country country = getCountryForFindAllCountriesFetchingCitiesQuery(rs, countryId);
                        countryMap.put(countryId, country);
                        accumulator.add(country);
                    }

                    Country country = countryMap.get(countryId);
                    String discrStr = rs.getString("DISCRIMINATOR");

                    if(discrStr != null) {
                        City city = getCityForFindAllCountriesFetchingCitiesQuery(rs, discrStr);

                        if (country.getCities() == null) {
                            country.setCities(new ArrayList<>());
                        }

                        country.getCities().add(city);
                    }
                }
            });
        } catch (Exception e) {
            throw new SqlException(e);
        }
    }

    private City getCityForFindAllCountriesFetchingCitiesQuery(ResultSet rs, String discrStr) {
        CityDiscriminator discriminator = CityDiscriminator.valueOf(discrStr);

        City city;
        switch (discriminator) {
            case BEACH_N_SIGHTSEE_N_SKI_RESORT:
                city = ModelMapper.mapBeachable(rs)
            case SKI_RESORT:
            case SIGHTSEE:
            case BEACH:
            case BEACH_N_SIGHTSEE:
            case BEACH_N_SKI_RESORT:
            case SIGHTSEE_N_SKI_RESORT:
        }
    }

    private Country getCountryForFindAllCountriesFetchingCitiesQuery(ResultSet rs, long countryId) throws SQLException {
        Country country = CountryMapper.mapCountry(rs);
        country.setId(countryId);
        country.setName(rs.getString("COUNTRY_NAME"));
        return country;
    }

    @Override
    public Country insert(Country country) {
        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertCountrySql(),
                    ps -> {
                        appendPreparedStatementParamsForCountry(new AtomicInteger(0), ps, country);
                    },
                    rs -> rs.getLong("ID"));

            if (generatedId.isPresent()) {
                country.setId(generatedId.get());
            } else {
                throw new KeyGeneratorException("ID");
            }

            return country;
        } catch (Exception e) {
            throw new SqlException(e);
        }
    }

    private void appendPreparedStatementParamsForCountry(AtomicInteger index, PreparedStatement ps, Country country) throws SQLException {
        ps.setString(index.incrementAndGet(), country.getName());
        ps.setString(index.incrementAndGet(), country.getLanguage());
    }

    private String getInsertCountrySql() {
        return "INSERT INTO COUNTRY ( NAME, LANGUAGE ) VALUES (?, ?)";
    }

    @Override
    public void insert(Collection<Country> countries) {
        try {
            QueryWrapper.executeUpdateAsBatch(getInsertCountrySql(), countries,
                    (ps, country) -> appendPreparedStatementParamsForCountry(new AtomicInteger(0), ps, country));
        } catch (Exception e) {
            throw new SqlException(e);
        }
    }

    @Override
    public void update(Country country) {
        String sql = "UPDATE COUNTRY SET NAME = ?, COUNTRY = ?, WHERE ID = ?";

        try {
            QueryWrapper.executeUpdate(sql,
                    ps -> {
                        AtomicInteger index = new AtomicInteger();
                        appendPreparedStatementParamsForCountry(index, ps, country);
                        ps.setLong(index.incrementAndGet(), country.getId());
                    });
        } catch (Exception e) {
            throw new SqlException(e);
        }
    }

    @Override
    public Optional<Country> findById(Long id) {
        try {
            String sql = "SELECT * FROM COUNTRY WHERE ID = ?";
            return QueryWrapper.selectOne(sql,
                    CountryMapper::mapCountry,
                    ps -> {
                        ps.setLong(1, id);
                    });
        } catch (Exception e) {
            throw new SqlException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            QueryWrapper.executeUpdate("DELETE FROM COUNTRY WHERE ID = ?", ps -> {
                ps.setLong(1, id);
            })
        } catch (Exception e) {
            throw new SqlException(e);
        }
    }

    @Override
    public void printAll() {
        findAllCountriesFetchingModels().forEach(System.out::println);
    }

    @Override
    public List<Country> findAll() {
        try {
            return QueryWrapper.select("SELECT * FROM COUNTRY", CountryMapper::mapCountry);
        } catch (Exception e) {
            throw new SqlException(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM MARK",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlException(e);
        }
    }
}
