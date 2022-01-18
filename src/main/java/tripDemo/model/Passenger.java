package tripDemo.model;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class Passenger {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;


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