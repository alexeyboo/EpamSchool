package hw2.travelcompany.user.repo.impl;

import hw2.travelcompany.user.domain.User;
import hw2.travelcompany.user.repo.UserRepo;

import static hw2.travelcompany.storage.Storage.usersList;

public class UserMemoryCollectionRepo implements UserRepo {
    @Override
    public void add(User user) {
        usersList.add(user);
    }

    @Override
    public User findById(long id) {
        return findUserById(id);
    }



    @Override
    public void deleteById(long id) {
        User found = findUserById(id);
        if (found != null)
            usersList.remove(found);
    }

    @Override
    public void printAll() {
        for (User user:usersList) {
            System.out.println(user);
        }
    }

    private User findUserById(long id) {
        for (User user : usersList) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }
}
