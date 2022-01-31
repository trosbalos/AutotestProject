package repositories;


import config.BaseConnection;
import dictionaries.ServiceEnum;
import org.hibernate.Session;
import tripDemo.model.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TripRepository extends BaseRepository {

    private static TripRepository instance;

    public static TripRepository getInstance() {
        if (instance == null) {
            instance = new TripRepository();
        }
        return instance;
    }

    protected TripRepository() {
        super(ServiceEnum.TRIP);
    }
    public <T> void delete(T object) {
        Session session = getSession();
        session.beginTransaction();
        session.remove(object);
        session.getTransaction().commit();
        closeSession();
    }
}