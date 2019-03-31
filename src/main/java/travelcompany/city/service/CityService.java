package travelcompany.city.service;

import travelcompany.city.domain.City;
import travelcompany.city.search.CitySearchCondition;
import travelcompany.common.solutions.service.BaseService;

import java.util.List;

public interface CityService extends BaseService <City, Long> {
    List<? extends City> search(CitySearchCondition searchCondition);
}
