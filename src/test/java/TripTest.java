import comporator.TripComparator;
import config.ConfigQA;
import dictionaries.IPathEnum;
import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import steps.TripSteps;
import tripDemo.model.Passenger;
import tripDemo.model.Trip;
import java.util.ArrayList;

import java.util.Map;



public class TripTest {

    private static Map<IPathEnum, String> serviceDataMap;
    private static Trip createTrip, putTrip;

    @BeforeClass
    public static void init() {
        serviceDataMap = ConfigQA.getInstance().getServiceDataMap();
        createTrip = new Trip.Builder()
                .withRandomMainInfo(1)
                .withPassengers(new ArrayList<>() {{
                    for (int i = 0; i < RandomUtils.nextInt(1, 3); i++) {
                        add(new Passenger.Builder().withRandomCompletely().build());
                    }
                }}).build();

        putTrip = new Trip.Builder()
                //Пока что нужно установить id существующей модели в базе
                .withId(8L)
                .withRandomMainInfo(1)
                .withPassengers(new ArrayList<>() {{
                    add(new Passenger.Builder()
                            .withId(10L)
                            .withRandomCompletely().build());
                }}).build();
    }

    @Test
    public void createTrip() {
        Trip responseTrip = TripSteps.sendPost(createTrip);
        new TripComparator(createTrip, responseTrip).compare();
    }

    @Test
    public void getTrip() {
        Trip responseTrip = TripSteps.sendGet(8);
        new TripComparator(putTrip, responseTrip).compare();
    }

    @Test
    public void putTrip() {
        Trip responseTrip = TripSteps.sendPut(putTrip);
        new TripComparator(putTrip, responseTrip).compare();
    }

    @Test
    public void deleteTrip() {
        Trip responseTrip = TripSteps.sendDelete(8);
        new TripComparator(putTrip, responseTrip).compare();
    }
}