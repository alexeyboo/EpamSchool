package hw2.travelcompany.user.service;

import hw2.travelcompany.common.business.service.BaseService;
import hw2.travelcompany.user.domain.User;

public interface UserService extends BaseService {
    void add(User user);

    User findById(Long id);

    void delete(User user);
}
