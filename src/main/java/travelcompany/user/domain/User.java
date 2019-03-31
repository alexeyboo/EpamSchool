package travelcompany.user.domain;

import travelcompany.common.business.domain.BaseDomain;
import travelcompany.order.domain.Order;

import java.util.List;

public class User extends BaseDomain<Long> {
    private String firstName;
    private String lastName;
    private Passport passport;
    private ClientType clientType;
    private List<Order> orders;

    public User() {}

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, Passport passport, ClientType clientType) {
        this(firstName, lastName);
        this.passport = passport;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport=" + passport +
                ", clientType=" + clientType +
                '}';
    }
}
