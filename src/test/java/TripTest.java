import comporator.TripComparator;
import comporator.TripDBComparator;

import hibernate.TripEntity;
import org.apache.commons.lang3.RandomUtils;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import repositories.TripRepository;
import steps.TripSteps;
import testDataProvider.TestDataProvider;
import tripDemo.model.Passenger;
import tripDemo.model.Trip;
import java.util.ArrayList;



public class TripTest extends BaseTest {

    private Trip trip;
    private TripEntity tripEntity;

    @BeforeMethod
    public void init() {
        trip = new Trip.Builder()
                .withRandomMainInfo(1)
                .withPassengers(new ArrayList<Passenger>() {{
                    for (int i = 0; i < RandomUtils.nextInt(1, 3); i++) {
                        add(new Passenger.Builder().withRandomCompletely().build());
                    }
                }}).build();
    }

    @BeforeMethod(onlyForGroups = {"withExistTrip"})
    public void prepareTrip() {
        trip = TripSteps.createTrip(trip);
    }

    @Test(groups = {"withAddedEntity"},dataProvider = "prepareTrips",dataProviderClass = TestDataProvider.class)
    public void createTrip(String testNumber, Trip trip) {
        System.out.println("test number = " + testNumber);
        Trip responseTrip = TripSteps.sendPost(trip);
        new TripComparator(responseTrip, trip).compare();
        tripEntity = TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId());
        new TripDBComparator(responseTrip, tripEntity).compare();
    }

    @Test(groups = {"withExistTrip", "withAddedEntity"})
    public void getTrip() {
        Trip responseTrip = TripSteps.sendGet(trip.getId());
        new TripComparator(trip, responseTrip).compare();
        tripEntity = TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId());
        new TripDBComparator(responseTrip, tripEntity).compare();
    }

    @Test(groups = {"withExistTrip", "withAddedEntity"})
    public void putTrip() {
        trip.setPlane("newPlane");
        Trip responseTrip = TripSteps.sendPut(trip);
        new TripComparator(trip, responseTrip).compare();
        tripEntity = TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId());
        new TripDBComparator(responseTrip, tripEntity).compare();
    }
    @AfterMethod(onlyForGroups = {"withAddedEntity"})
    public void deleteEntity() {
        TripRepository.getInstance().delete(tripEntity);
    }
    @Test(groups = {"withExistTrip"})
    public void deleteTrip() {
        Trip responseTrip = TripSteps.sendDelete(trip.getId());
        new TripComparator(trip, responseTrip).compare();
        tripEntity = TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId());
        Assertions.assertThat(tripEntity).isNull();
    }
}