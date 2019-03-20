package hw2.travelcompany.city.service;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.search.CitySearchCondition;
import hw2.travelcompany.common.solutions.service.BaseService;

import java.util.List;

public interface CityService extends BaseService <City, Long> {

    List<? extends City> search(CitySearchCondition searchCondition);

}
