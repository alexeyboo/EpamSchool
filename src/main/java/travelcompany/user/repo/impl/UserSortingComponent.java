package travelcompany.user.repo.impl;

import travelcompany.user.domain.User;
import travelcompany.user.search.UserSearchCondition;
import travelcompany.user.search.UserSortByField;

import java.util.Comparator;
import java.util.List;

public class UserSortingComponent {
    public void applySorting(List<? extends User> users, UserSearchCondition searchCondition) {
        Comparator<User> userComparator = null;
        UserSortByField field = searchCondition.getSortByField();

        switch (searchCondition.getSortType()) {
            case SIMPLE: {
                userComparator = UserComporatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                userComparator = UserComporatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (userComparator != null) {
            switch (searchCondition.getSortDirection()) {
                case ASC:
                    users.sort(userComparator);
                    break;
                case DESC:
                    users.sort(userComparator.reversed());
                    break;
            }
        }
    }
}
