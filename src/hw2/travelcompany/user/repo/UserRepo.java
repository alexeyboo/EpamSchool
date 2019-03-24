package hw2.travelcompany.user.repo;

import hw2.travelcompany.common.solutions.repo.BaseRepo;
import hw2.travelcompany.user.domain.User;
import hw2.travelcompany.user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo <User, Long>{
    List<? extends User> search(UserSearchCondition searchCondition);
}
