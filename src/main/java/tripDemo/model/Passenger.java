package tripDemo.model;

import com.github.javafaker.Faker;
import lombok.Data;
import org.apache.commons.lang3.builder.CompareToBuilder;

@Data
public class Passenger implements Comparable<Passenger> {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;

    @Override
    public int compareTo(Passenger o) {
        return new CompareToBuilder()
                .append(getId(), o.getId())
                .append(getFirstName(), o.getFirstName())
                .append(getMiddleName(), o.getMiddleName())
                .append(getLastName(), o.getLastName())
                .toComparison();
    }


    public static class Builder {
        private final Passenger passenger;
        private final Faker faker = new Faker();

        public Builder() {
            passenger = new Passenger();
        }

        public Builder withId(Long id) {
            passenger.id = id;
            return this;
        }

        public Builder withRandomCompletely() {
            passenger.firstName = faker.address().firstName();
            passenger.middleName = faker.name().firstName();
            passenger.lastName = faker.address().lastName();
            return this;
        }

        public Passenger build() {
            return passenger;
        }
    }
}