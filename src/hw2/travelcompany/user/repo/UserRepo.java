package hw2.travelcompany.user.repo;

import hw2.travelcompany.common.business.repo.BaseRepo;
import hw2.travelcompany.user.domain.User;

public interface UserRepo extends BaseRepo {
    void add(User user);

    User findById(long id);
}
