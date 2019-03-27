package travelcompany.user.repo.impl;

import com.sun.xml.internal.ws.server.sei.SEIInvokerTube;
import hw2.travelcompany.user.domain.User;
import hw2.travelcompany.user.search.UserSortByField;

import java.util.*;

import static hw2.travelcompany.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static hw2.travelcompany.user.search.UserSortByField.*;

public class UserComporatorComponent {

    private static final UserComporatorComponent INSTANCE = new UserComporatorComponent();
    private static Map<UserSortByField, Comparator<User>> comparatorsByField = new HashMap<>();

    //for complex
    private static Set<UserSortByField> fieldComparePriorityOrder =
            new LinkedHashSet<>(Arrays.asList(FIRST_NAME, LAST_NAME, PASSPORT, CLIENT_TYPE));

    static {
        comparatorsByField.put(FIRST_NAME, getComparatorForFirstNameField());
        comparatorsByField.put(LAST_NAME, getComparatorForLastNameField());
        comparatorsByField.put(PASSPORT, getComparatorForPassportField());
        comparatorsByField.put(CLIENT_TYPE, getComparatorForClientTypeField());
    }

    public Comparator<User> getComparatorForField(UserSortByField field) {
        return comparatorsByField.get(field);
    }

    private static Comparator<User> getComparatorForClientTypeField() {
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return getComparatorForNullableStrings()
                        .compare(o1.getClientType().getClass().getName(), o2.getClientType().getClass().getName());
            }
        };
    }

    private static Comparator<User> getComparatorForFirstNameField(){
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return getComparatorForNullableStrings().compare(o1.getFirstName(), o2.getFirstName());
            }
        };
    }
    private static Comparator<User> getComparatorForLastNameField(){
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return getComparatorForNullableStrings().compare(o1.getLastName(), o2.getLastName());
            }
        };
    }
    private static Comparator<User> getComparatorForPassportField(){
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getPassport().compareTo(o2.getPassport());
            }
        };
    }
    public Comparator<User> getComplexComparator(UserSortByField field) {
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = 0;
                Comparator<User> userComparator = comparatorsByField.get(field);

                if (userComparator != null) {
                    result = userComparator.compare(o1, o2);

                    if (result == 0) {
                        for (UserSortByField otherField : fieldComparePriorityOrder) {
                            if (!otherField.equals(field)){
                                result = comparatorsByField.get(otherField).compare(o1, o2);
                                if (result != 0) {
                                    break;
                                }
                            }
                        }
                    }
                }
                return result;
            }
        };
    }

    private UserComporatorComponent() {
    }

    public static UserComporatorComponent getInstance(){
        return INSTANCE;
    }

}
