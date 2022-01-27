package repositories;


import config.BaseConnection;
import dictionaries.ServiceEnum;
import tripDemo.model.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TripRepository {

    private final Connection connection = BaseConnection.getInstance().getConnection(ServiceEnum.TRIP);

    public Trip getById(long id) {
        String sql = "select * from trip where id = ?";
        ResultSet resultSet;
        Trip trip = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            if (resultSet.next()) {
                trip = new Trip();
                trip.setId(resultSet.getLong("id"));
                trip.setCompanyId(resultSet.getLong("company_id"));
                trip.setPlane(resultSet.getString("plane"));
                trip.setTownFrom(resultSet.getString("town_from"));
                trip.setTownTo(resultSet.getString("town_to"));
                trip.setTimeOut(LocalDateTime.from(dateTimeFormatter.parse(resultSet.getString("time_out"))));
                trip.setTimeIn(LocalDateTime.from(dateTimeFormatter.parse(resultSet.getString("time_in"))));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return trip;
    }
}