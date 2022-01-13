
import org.junit.jupiter.api.*;
import tripDemo.model.Passenger;
import tripDemo.model.Trip;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TripTest {



    @Test
    @Order(2)
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
        passenger2.setFirstName("Andrey");
        passenger2.setMiddleName("Volkov");
        passenger2.setLastName("Pupkin");
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
                .body()
                .as(Trip.class);

    }

    @Test
    @Order(3)
    public void getTrip() {
        Map<String, String> actual = given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get("http://localhost:8080/trip/getTrip/4")
                .then()
                .statusCode(200)
                .extract().body()
                .jsonPath().getMap("", String.class, String.class);
        Assertions.assertEquals(actual.get("townFrom"),"Moscow");


        // тут у меня подсветило equalTo("Moscow") в методе body()
        // .body("townFrom", equalTo("Moscow"));
    }

    @Test
    @Order(4)
    public void putTrip() {
        Trip trip = new Trip();
        trip.setId(4L);
        trip.setCompanyId(1L);
        trip.setPlane("plane3");
        trip.setTownTo("Vladivostok");
        //Вызов метода put должен вернуть объект Trip
        Trip tripResult = given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .body(trip)
                .when()
                .put("http://localhost:8080/trip/putTrip")
                .as(Trip.class);
        Assertions.assertEquals(tripResult.getId(),trip.getId());
        Assertions.assertEquals(tripResult.getCompanyId(),trip.getCompanyId());
        Assertions.assertEquals(tripResult.getPlane(),trip.getPlane());
        Assertions.assertEquals(tripResult.getTownTo(),trip.getTownTo());

    }
    @Test
    public void deleteTrip() {
        Trip trip = new Trip();
          given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .body(trip)
                .when()
                .delete("http://localhost:8080/trip/deleteTrip/7");
        given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get("http://localhost:8080/trip/getTrip/7")
                .then()
                .assertThat()
                .statusCode(500);
    }
}
