package travelcompany.city.repo;

import travelcompany.city.domain.City;
import travelcompany.city.search.CitySearchCondition;
import travelcompany.common.solutions.repo.BaseRepo;

import java.util.List;

<<<<<<< HEAD
public interface CityRepo extends BaseRepo <City, Long>{

    List<? extends City> search(CitySearchCondition searchCondition);

}
=======
public interface CityRepo extends BaseRepo<City, Long> {

    List<? extends City> search(CitySearchCondition searchCondition);

}
>>>>>>> github/master
