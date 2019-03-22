package hw2.travelcompany.user.service.impl;

import hw2.travelcompany.user.domain.User;
import hw2.travelcompany.user.repo.UserRepo;
import hw2.travelcompany.user.service.UserService;

public class UserDefaultService implements UserService {

    private final UserRepo userRepo;

    public UserDefaultService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void insert(User user) {
        if (user != null) {
            userRepo.insert(user);
        }
    }

    @Override
    public User findById(Long id) {
        if (id != null) {
            return userRepo.findById(id);
        }
    }

    @Override
    public void delete(User user) {
        if (user != null) {
            this.deleteById(user.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            userRepo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        userRepo.printAll();
    }
}
