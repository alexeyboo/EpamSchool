package hw2.travelcompany.user.service;

import hw2.travelcompany.common.solutions.service.BaseService;
import hw2.travelcompany.user.domain.User;
import hw2.travelcompany.user.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService <User, Long> {

    List<? extends User> search(UserSearchCondition searchCondition);
}
