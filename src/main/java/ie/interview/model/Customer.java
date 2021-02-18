package ie.interview.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Customer implements Comparable<Customer> {

    private double latitude;
    private int userId;
    private String name;
    private double longitude;

    @JsonCreator
    public Customer(@JsonProperty(value = "latitude", required = true) double latitude,
                    @JsonProperty(value = "user_id", required = true) int userId,
                    @JsonProperty(value = "name", required = true) String name,
                    @JsonProperty(value = "longitude", required = true) double longitude) {

        this.latitude = latitude;
        this.userId = userId;
        this.name = name;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Double.compare(customer.latitude, latitude) == 0 &&
                userId == customer.userId &&
                Double.compare(customer.longitude, longitude) == 0 &&
                name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, userId, name, longitude);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "latitude=" + latitude +
                ", user_id=" + userId +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public int compareTo(final Customer o) {
        return Integer.compare(this.userId, o.getUserId());
    }

}
