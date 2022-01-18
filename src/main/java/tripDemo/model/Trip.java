package tripDemo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.github.javafaker.Faker;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Data
public class Trip {
    private final List<Passenger> passengerList = new ArrayList<>();
    private Long id;
    private Long companyId;
    private String plane;
    private String townFrom;
    private String townTo;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeOut;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeIn;

    public static class Builder {
        private final Trip trip;
        private final Faker faker = new Faker();

        public Builder() {
            trip = new Trip();
        }

        public Builder withPassengers(List<Passenger> passengerList) {
            trip.passengerList.addAll(passengerList);
            return this;
        }

        public Builder withPlane(String plane) {
            trip.plane = plane;
            return this;
        }
        public Builder withId(Long id) {
            trip.id = id;
            return this;
        }

        public Builder withTownTo(String townTo) {
            trip.townTo = townTo;
            return this;
        }

        public Builder withRandomMainInfo(long companyId) {
            trip.companyId = companyId;
            trip.plane = faker.company().name();
            trip.townFrom = faker.address().cityName();
            trip.townTo = faker.address().city();
            trip.timeOut = LocalDateTime.now().plusDays(5).truncatedTo(ChronoUnit.SECONDS);
            trip.timeIn = LocalDateTime.now().plusDays(5).plusHours(5).truncatedTo(ChronoUnit.SECONDS);
            return this;
        }

        public Trip build() {
            return trip;
        }
    }
}