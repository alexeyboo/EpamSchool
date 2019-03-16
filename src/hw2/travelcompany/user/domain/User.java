package hw2.travelcompany.user.domain;

import hw2.travelcompany.common.business.domain.BaseDomain;
import hw2.travelcompany.order.domain.Order;

import java.util.ArrayList;

public class User extends BaseDomain {

    private Long id;
    private String name;
    private String lastName;
    private Passport passport;
    private ClientType clientType;
    private ArrayList<Order> orders;

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }



    public void addOrders(Order... orders) {
        for (int i = 0; i < orders.length; i++) {
            this.orders.add(orders[i]);
        }
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public User(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public User(String name, String lastName, Passport passport, ClientType clientType) {
        this(name, lastName);
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
