package domain;

import base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Address extends BaseEntity<Long> {

    private static final String POSTAL_ADDRESS = "postal_address";

    @Column(nullable = false)
    private String number;
    private String state;
    private String city;

    @Column(name = POSTAL_ADDRESS, columnDefinition = "text")
    private String postalAddress;

    private Long postalCode;

    public Address() {

    }

    public Address(String number, String city, String postalAddress, Long postalCode) {
        this.number = number;
        this.city = city;
        this.postalAddress = postalAddress;
        this.postalCode = postalCode;
    }

    public Address(String number, String state, String city, String postalAddress, Long postalCode) {
        this(number, city, postalAddress, postalCode);
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "number='" + number + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", postalAddress='" + postalAddress + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
