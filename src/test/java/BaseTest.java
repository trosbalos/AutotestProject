import config.ConfigQA;
import dictionaries.IPathEnum;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

public abstract class BaseTest {
    protected ThreadLocal<Map<IPathEnum, String>> serviceDataMap = new ThreadLocal<>();


    @BeforeSuite
    public void initBase() {
        serviceDataMap.set(ConfigQA.getInstance().getServiceDataMap());

    }

}