package comporator;

import tripDemo.model.Passenger;
import tripDemo.model.Trip;

import static org.assertj.core.api.Assertions.assertThat;

public class TripComparator implements IComparator {
    private final Trip actual, expected;

    public TripComparator(Trip actual, Trip expected) {
        this.actual = actual;
        this.expected = expected;
    }

    public void compare() {
        assertThat(actual.getCompanyId()).isEqualTo(expected.getCompanyId());
        assertThat(actual.getPlane()).isEqualTo(expected.getPlane());
        assertThat(actual.getTownFrom()).isEqualTo(expected.getTownFrom());
        assertThat(actual.getTownTo()).isEqualTo(expected.getTownTo());
        assertThat(actual.getTimeOut()).isEqualTo(expected.getTimeOut());
        assertThat(actual.getTimeIn()).isEqualTo(expected.getTimeIn());
        assertThat(actual.getPassengerList()).hasSameSizeAs(expected.getPassengerList());
        for (int i = 0; i < actual.getPassengerList().size(); i++) {
            Passenger actualPassenger = actual.getPassengerList().get(i);
            Passenger expectedPassenger = expected.getPassengerList().get(i);
            assertThat(actualPassenger.getFirstName()).isEqualTo(expectedPassenger.getFirstName());
            assertThat(actualPassenger.getMiddleName()).isEqualTo(expectedPassenger.getMiddleName());
            assertThat(actualPassenger.getLastName()).isEqualTo(expectedPassenger.getLastName());
        }
    }

    public void compareTrip() {
        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("id", "passengerList.id")
                .isEqualTo(expected);

    }
}