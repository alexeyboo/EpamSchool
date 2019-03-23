package hw2.travelcompany.user.repo.impl;

import hw2.travelcompany.common.solutions.utils.ArrayUtils;
import hw2.travelcompany.user.domain.User;
import hw2.travelcompany.user.repo.UserRepo;
import static hw2.travelcompany.storage.Storage.usersArray;

public class UserMemoryArrayRepo implements UserRepo {

    private int userIndex = 0;

    @Override
    public void insert(User user) {
        if (userIndex == usersArray.length) {
            User[] newUsers = new User[usersArray.length * 2];
            System.arraycopy(usersArray, 0, newUsers, 0, usersArray.length);
            usersArray = newUsers;
        }
        usersArray[userIndex] = user;
        userIndex++;
    }

    @Override
    public User findById(Long id) {
        Integer userIndex = findUserByID(id);
        if (userIndex != null)
            return usersArray[userIndex];
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Integer userIndex = findUserIndexById(id);

        if (userIndex != null) {
            deleteUserByIndex(userIndex);
        }
    }

    @Override
    public void printAll() {
        for (User user: usersArray) {
            System.out.println(user);
        }
    }

    private Integer findUserByID(Long userId) {
        for (int i = 0; i < usersArray.length; i++) {
            if (usersArray[i].getId().equals(userId)) {
                return i;
            }
        }
        return null;
    }
    private Integer findUserIndexById(Long id) {
        for (int i = 0; i < usersArray.length; i++) {
            if (usersArray[i].getId().equals(id))
                return i;
        }
        return null;
    }
    private void deleteUserByIndex(Integer userIndex) {
        ArrayUtils.removeElement(usersArray, userIndex);
        userIndex--;
    }

}
