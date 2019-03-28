package travelcompany.user.search;

import travelcompany.common.business.search.BaseSearchCondition;
import travelcompany.order.domain.Order;
import travelcompany.user.domain.ClientType;
import travelcompany.user.domain.Passport;

import static travelcompany.common.solutions.utils.StringUtils.isNotBlank;

public class UserSearchCondition extends BaseSearchCondition<Long> {
    private String firstName;
    private String lastName;
    private Passport passport;
    private ClientType clientType;
    private Order order;
    private UserSortByField sortByField;

    public boolean searchByFirstName() {
        return isNotBlank(firstName);
    }

    public boolean searchByLastName() {
        return isNotBlank(lastName);
    }

    public boolean searchByPassport() {
        return passport != null;
    }

    public boolean searchByClientType() {
        return clientType != null;
    }

    public boolean searchByOrder() {
        return order != null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UserSortByField getSortByField() {
        return sortByField;
    }

    public void setSortByField(UserSortByField sortByField) {
        this.sortByField = sortByField;
    }

    @Override
    public boolean needSorting() {
        return super.needSorting() && sortByField != null;
    }
}
