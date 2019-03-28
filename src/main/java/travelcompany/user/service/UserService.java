package travelcompany.user.service;

import travelcompany.common.solutions.service.BaseService;
import travelcompany.user.domain.User;
import travelcompany.user.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService <User, Long> {

    List<? extends User> search(UserSearchCondition searchCondition);
}
