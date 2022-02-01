package steps;

import config.ConfigQA;
import dictionaries.IPathEnum;
import dictionaries.TripPathEnum;
import helper.ApiHelper;
import hibernate.TripEntity;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import jsongenerator.JsonGenerator;
import mapper.TripMapper;
import org.assertj.core.api.Assertions;
import repositories.TripRepository;
import tripDemo.model.Trip;

import java.util.Collections;
import java.util.Map;

public class TripSteps {

    private static final Map<IPathEnum, String> serviceDataMap = ConfigQA.getInstance().getServiceDataMap();

    @Step("Вызов метода post")
    public static Trip sendPost(Trip trip) {
        String path = serviceDataMap.get(TripPathEnum.CREATE_TRIP);
        Response response = ApiHelper.post(path, JsonGenerator.toJsonString(trip));
        return doCommonOperation(response);
    }

    @Step("Вызов метода get")
    public static Trip sendGet(long id) {
        String path = serviceDataMap.get(TripPathEnum.GET_TRIP);
        Response response = ApiHelper.get(path, id);
        return doCommonOperation(response);
    }

    @Step("Вызов метода put")
    public static Trip sendPut(Trip trip) {
        String path = serviceDataMap.get(TripPathEnum.PUT_TRIP);
        Response response = ApiHelper.put(path, JsonGenerator.toJsonString(trip));
        return doCommonOperation(response);
    }

    @Step("Вызов метода delete")
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

    @Step("Вызов метода createTrip")
    public static synchronized Trip createTrip(Trip trip) {
        TripMapper tripMapper = TripMapper.INSTANCE;
        TripEntity tripEntity = tripMapper.toEntity(trip);
        tripEntity = TripRepository.getInstance().create(tripEntity);
        return tripMapper.toDto(tripEntity);
    }
}