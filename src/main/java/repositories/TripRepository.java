package repositories;

import dictionaries.ServiceEnum;


public class TripRepository extends BaseRepository {

    private static TripRepository instance;

    protected TripRepository() {
        super(ServiceEnum.TRIP);
    }

    public static TripRepository getInstance() {
        if (instance == null) {
            instance = new TripRepository();
        }
        return instance;
    }

}