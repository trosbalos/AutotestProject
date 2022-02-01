import comporator.TripComparator;
import comporator.TripDBComparator;
import hibernate.TripEntity;
import io.qameta.allure.Description;
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
    ThreadLocal<Trip> trip = new ThreadLocal<>();
    ThreadLocal<TripEntity> tripEntity = new ThreadLocal<>();


    @BeforeMethod
    public void init() {
        trip.set(new Trip.Builder()
                .withRandomMainInfo(1)
                .withPassengers(new ArrayList<Passenger>() {{
                    for (int i = 0; i < RandomUtils.nextInt(1, 3); i++) {
                        add(new Passenger.Builder().withRandomCompletely().build());
                    }
                }}).build());
    }

    @BeforeMethod(onlyForGroups = {"withExistTrip"})
    public void prepareTrip() {
        trip.set(TripSteps.createTrip(trip.get()));
    }

    @Test(groups = {"withAddedEntity"}, dataProvider = "prepareTrips", dataProviderClass = TestDataProvider.class)
    @Description("Проверка метода post")
    public void createTrip(String testNumber, Trip trip) {
        System.out.println("test number = " + testNumber);
        Trip responseTrip = TripSteps.sendPost(trip);
        new TripComparator(responseTrip, trip).compare();
        tripEntity.set(TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId()));
        new TripDBComparator(responseTrip, tripEntity.get()).compare();
    }

    @Test(groups = {"withExistTrip", "withAddedEntity"})
    @Description("Проверка метода get")
    public void getTrip() {
        Trip responseTrip = TripSteps.sendGet(trip.get().getId());
        System.out.println(responseTrip.toString());
        new TripComparator(trip.get(), responseTrip).compare();
        tripEntity.set(TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId()));
        new TripDBComparator(responseTrip, tripEntity.get()).compare();
    }

    @Test(groups = {"withExistTrip", "withAddedEntity"})
    @Description("Проверка метода put")
    public void putTrip() {
        trip.get().setPlane("newPlane");
        Trip responseTrip = TripSteps.sendPut(trip.get());
        new TripComparator(trip.get(), responseTrip).compare();
        tripEntity.set(TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId()));
        new TripDBComparator(responseTrip, tripEntity.get()).compare();
    }

    @AfterMethod(onlyForGroups = {"withAddedEntity"})
    public void deleteEntity() {
        TripRepository.getInstance().delete(tripEntity.get());
    }

    @Test(groups = {"withExistTrip"})
    @Description("Проверка метода delete")
    public void deleteTrip() {
        Trip responseTrip = TripSteps.sendDelete(trip.get().getId());
        new TripComparator(trip.get(), responseTrip).compare();
        tripEntity.set(TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId()));
        Assertions.assertThat(tripEntity.get()).isNull();
    }
}