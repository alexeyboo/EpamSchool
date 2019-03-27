package travelcompany.user.repo.impl;

import hw2.travelcompany.storage.SequenceGenerator;
import hw2.travelcompany.user.domain.User;
import hw2.travelcompany.user.repo.UserRepo;
import hw2.travelcompany.user.search.UserSearchCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hw2.travelcompany.storage.Storage.usersArray;
import static hw2.travelcompany.storage.Storage.usersList;

public class UserMemoryCollectionRepo implements UserRepo {

    private UserSortingComponent sortingComponent = new UserSortingComponent();

    @Override
    public void insert(User user) {
        usersList.add(user);
        user.setId(SequenceGenerator.getNextValue());
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User findById(Long id) {
        return findUserById(id);
    }

    @Override
    public List<? extends User> search(UserSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            List<? extends User> result = doSearch(searchCondition);
            boolean needSorting = !result.isEmpty() && searchCondition.needSorting();

            if (needSorting) {
                sortingComponent.applySorting(result, searchCondition);
            }
            return result;
        }
    }

    private List<? extends User> doSearch(UserSearchCondition searchCondition) {
        List<User> result = new ArrayList<>();

        for (User user : usersArray) {
            if (user != null) {
                boolean found = true;

                if (searchCondition.searchByFirstName()) {
                    found = searchCondition.getFirstName().equals(user.getFirstName());
                }
                if (found && searchCondition.searchByLastName()) {
                    found = searchCondition.getLastName().equals(user.getLastName());
                }
                if (found && searchCondition.searchByPassport()) {
                    found = searchCondition.getPassport().equals(user.getPassport());
                }
                if (found && searchCondition.searchByClientType()) {
                    found = searchCondition.getClientType().equals(user.getClientType());
                }
                if (found && searchCondition.searchByOrder()) {
                    found = searchCondition.getOrder().equals(user.getClientType());
                }
                if (found) {
                    result.add(user);
                }
            }
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        User found = findUserById(id);

        if (found != null) {
            usersList.remove(found);
        }
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


    @Override
    public List<User> findAll() {
        return usersList;
    }
}
