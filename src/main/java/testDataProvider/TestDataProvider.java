package testDataProvider;

import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.DataProvider;
import tripDemo.model.Passenger;
import tripDemo.model.Trip;

import java.util.ArrayList;

public class TestDataProvider {
    @DataProvider
    public Object[][] prepareTrips() {
        return new Object[][]{
                {"1", new Trip.Builder()
                        .withRandomMainInfo(1)
                        .withPassengers(new ArrayList<Passenger>() {{
                            for (int i = 0; i < RandomUtils.nextInt(1, 3); i++) {
                                add(new Passenger.Builder().withRandomCompletely().build());
                            }
                        }}).build()},
                {"2", new Trip.Builder()
                        .withRandomMainInfo(1)
                        .withPassengers(new ArrayList<Passenger>() {{
                            for (int i = 0; i < RandomUtils.nextInt(1, 3); i++) {
                                add(new Passenger.Builder().withRandomCompletely().build());
                            }
                        }}).build()},
        };
    }
}
