
import org.junit.Assert;
import org.junit.Test;
import tripDemo.model.Passenger;
import tripDemo.model.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TripTest {


    @Test
    public void createTrip() {
        Trip trip = new Trip();
        trip.setCompanyId(1L);
        trip.setPlane("plane2");
        trip.setTownFrom("Moscow");
        trip.setTownTo("St. Petersburg");
        trip.setTimeIn("2021-05-16T03:31:43");
        trip.setTimeOut("2021-05-16T03:31:43");

        List<Passenger> passengerList = new ArrayList<>();
        Passenger passenger1 = new Passenger();
        passenger1.setFirstName("Andrey");
        passenger1.setMiddleName("Volkov");
        passenger1.setLastName("Pupkin");

        Passenger passenger2 = new Passenger();
        passenger1.setFirstName("Andrey");
        passenger1.setMiddleName("Volkov");
        passenger1.setLastName("Pupkin");
        passengerList.add(passenger1);
        passengerList.add(passenger2);

        trip.setPassengerList(passengerList);

        given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .body(trip)
                .when()
                .post("http://localhost:8080/trip/createTrip")
                .getBody()
                .prettyPrint();

    }

    @Test
    public void getTrip() {
        Map<String, String> actual = given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get("http://localhost:8080/trip/getTrip/1")
                .then()
                .statusCode(200)
                .extract().body()
                .jsonPath().getMap("", String.class, String.class);
            Assert.assertEquals(actual.get("townFrom"),"Moscow");


        // тут у меня подсветило equalTo("Moscow") в методе body()
        // .body("townFrom", equalTo("Moscow"));
    }

    @Test
    public void createAnotherTrip() {
        Trip trip = new Trip();
        trip.setCompanyId(1L);
        trip.setPlane("plane2");
        trip.setTownFrom("Moscow");
        trip.setTownTo("St. Petersburg");
        trip.setTimeIn("2021-05-16T03:31:43");
        trip.setTimeOut("2021-05-16T03:31:43");

        List<Passenger> passengerList = new ArrayList<>();
        Passenger passenger1 = new Passenger();
        passenger1.setFirstName("Andrey");
        passenger1.setMiddleName("Volkov");
        passenger1.setLastName("Pupkin");

        Passenger passenger2 = new Passenger();
        passenger1.setFirstName("Andrey");
        passenger1.setMiddleName("Volkov");
        passenger1.setLastName("Pupkin");
        passengerList.add(passenger1);
        passengerList.add(passenger2);

        trip.setPassengerList(passengerList);
         given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .body(trip)
                .when()
                .post("http://localhost:8080/trip/createTrip")
                .as(Trip.class);

    }
}
