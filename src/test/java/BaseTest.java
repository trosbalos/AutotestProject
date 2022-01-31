import config.ConfigQA;
import dictionaries.IPathEnum;
import hibernate.TripEntity;
import mapper.TripMapper;
import org.testng.annotations.BeforeSuite;
import repositories.TripRepository;
import tripDemo.model.Trip;

import java.util.Map;

public abstract class BaseTest {
    protected Map<IPathEnum, String> serviceDataMap;

    @BeforeSuite
    public void initBase() {
        serviceDataMap = ConfigQA.getInstance().getServiceDataMap();

    }

}