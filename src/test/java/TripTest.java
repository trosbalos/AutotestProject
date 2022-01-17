import io.restassured.response.Response;
import jsongenerator.JsonGenerator;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.*;
import tripDemo.model.Passenger;
import tripDemo.model.Trip;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TripTest {


    @Order(1)
    @Test
    public void createTrip() {
        Trip trip = new Trip.Builder()
                .withRandomMainInfo(1)
                .withPassengers(new ArrayList<>() {{
                    for (int i = 0; i < RandomUtils.nextInt(1, 3); i++) {
                        add(new Passenger.Builder().withRandomCompletely().build());
                    }
                }}).build();
        String body = JsonGenerator.toJsonString(trip);

        Response response = given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .body(body)
                .when()
                .post("http://localhost:8080/trip/createTrip")
                .thenReturn();

        System.out.println(response.getBody().prettyPrint());
    }

    @Test
    @Order(2)
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
        Assertions.assertEquals(actual.get("townFrom"), "Moscow");

    }

    @Test
    @Order(3)
    public void putTrip() {
        Trip trip = new Trip();
        trip.setId(4L);
        trip.setCompanyId(1L);
        trip.setPlane("plane3");
        trip.setTownTo("Vladivostok");
        Trip tripResult = given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .body(trip)
                .when()
                .put("http://localhost:8080/trip/putTrip")
                .as(Trip.class);
        Assertions.assertEquals(tripResult.getId(), trip.getId());
        Assertions.assertEquals(tripResult.getCompanyId(), trip.getCompanyId());
        Assertions.assertEquals(tripResult.getPlane(), trip.getPlane());
        Assertions.assertEquals(tripResult.getTownTo(), trip.getTownTo());

    }

    @Test
    @Order(4)
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
