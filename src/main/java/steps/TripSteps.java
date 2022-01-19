package steps;

import config.ConfigQA;
import dictionaries.IPathEnum;
import dictionaries.TripPathEnum;
import helper.ApiHelper;
import io.restassured.response.Response;
import jsongenerator.JsonGenerator;
import org.assertj.core.api.Assertions;
import tripDemo.model.Trip;

import java.util.Collections;
import java.util.Map;

public class TripSteps {

    private static final Map<IPathEnum, String> serviceDataMap = ConfigQA.getInstance().getServiceDataMap();

    public static Trip sendPost(Trip trip) {
        String path = serviceDataMap.get(TripPathEnum.CREATE_TRIP);
        Response response = ApiHelper.post(path, JsonGenerator.toJsonString(trip));
        return doCommonOperation(response);
    }

    public static Trip sendGet(long id) {
        String path = serviceDataMap.get(TripPathEnum.GET_TRIP);
        Response response = ApiHelper.get(path, id);
        return doCommonOperation(response);
    }

    public static Trip sendPut(Trip trip) {
        String path = serviceDataMap.get(TripPathEnum.PUT_TRIP);
        Response response = ApiHelper.put(path, JsonGenerator.toJsonString(trip));
        return doCommonOperation(response);
    }

    public static Trip sendDelete(long id) {
        String path = serviceDataMap.get(TripPathEnum.DELETE_TRIP);
        Response response = ApiHelper.delete(path, id);
        return doCommonOperation(response);
    }

    private static Trip doCommonOperation(Response response) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Trip responseTrip = response.as(Trip.class);
        Collections.sort(responseTrip.getPassengerList());
        return responseTrip;
    }
}