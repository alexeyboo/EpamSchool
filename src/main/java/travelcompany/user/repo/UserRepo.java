package travelcompany.user.repo;

import travelcompany.common.solutions.repo.BaseRepo;
import travelcompany.user.domain.User;
import travelcompany.user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo <User, Long>{
    List<? extends User> search(UserSearchCondition searchCondition);
}
