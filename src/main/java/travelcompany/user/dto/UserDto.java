package travelcompany.user.dto;

import travelcompany.common.business.dto.BaseDto;
import travelcompany.user.domain.ClientType;
import travelcompany.user.domain.Passport;

public abstract class UserDto extends BaseDto<Long> {
    private String firstName;
    private String lastName;
    private Passport passport;
    private ClientType clientType;

    public UserDto() {}

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
}
