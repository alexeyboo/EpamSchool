package hw2.travelcompany.user.repo.impl;

import hw2.travelcompany.common.solutions.utils.ArrayUtils;
import hw2.travelcompany.user.domain.User;
import hw2.travelcompany.user.repo.UserRepo;
import static hw2.travelcompany.storage.Storage.users;

public class UserMemoryArrayRepo implements UserRepo {

    private int userIndex = 0;

    @Override
    public void add(User user) {
        if (userIndex == users.length) {
            User[] newUsers = new User[users.length * 2];
            System.arraycopy(users, 0, newUsers, 0, users.length);
            users = newUsers;
        }
        users[userIndex] = user;
        userIndex++;
    }

    @Override
    public User findById(long id) {
        Integer userIndex = findUserByID(id);
        if (userIndex != null)
            return users[userIndex];
        return null;
    }

    @Override
    public void deleteById(long id) {
        Integer userIndex = findUserIndexById(id);

        if (userIndex != null) {
            deleteUserByIndex(userIndex);
        }
    }

    @Override
    public void printAll() {
        for (User user: users) {
            System.out.println(user);
        }
    }

    private Integer findUserByID(Long userId) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getId().equals(userId)) {
                return i;
            }
        }
        return null;
    }
    private Integer findUserIndexById(long id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getId().equals(id))
                return i;
        }
        return null;
    }
    private void deleteUserByIndex(Integer userIndex) {
        ArrayUtils.removeElement(users, userIndex);
        userIndex--;
    }

}
