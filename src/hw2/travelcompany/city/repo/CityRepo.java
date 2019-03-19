package hw2.travelcompany.city.repo;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.search.CitySearchCondition;
import hw2.travelcompany.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo <City, Long>{

    List<? extends City> search(CitySearchCondition searchCondition);

}
