import com.github.javafaker.Faker;
import hibernate.CompanyEntity;
import hibernate.PassengerEntity;
import hibernate.TripEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class Main {

    public static void main(String[] args) {


        Faker faker = new Faker();

        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(TripEntity.class);
        configuration.addAnnotatedClass(CompanyEntity.class);
        configuration.addAnnotatedClass(PassengerEntity.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();

        //Генерация тестовой модели
        TripEntity trip = new TripEntity();
        PassengerEntity passenger = new PassengerEntity ();
        passenger.setFirstName(faker.address().firstName());
        passenger.setMiddleName(faker.name().firstName());
        passenger.setLastName(faker.address().lastName());

        PassengerEntity passenger2 = new PassengerEntity ();
        passenger2.setFirstName(faker.address().firstName());
        passenger2.setMiddleName(faker.name().firstName());
        passenger2.setLastName(faker.address().lastName());

        trip.getPassengerList().add(passenger);
        trip.getPassengerList().add(passenger2);

        CompanyEntity company = new CompanyEntity ();
        company.setId(1L);
        company.setName(faker.company().name());
        company.setOpenDate(LocalDateTime.now().plusDays(5).truncatedTo(ChronoUnit.SECONDS).toLocalDate());

        trip.setCompany(company);
        trip.setPlane(faker.cat().name());
        trip.setTownFrom(faker.address().cityName());
        trip.setTownTo(faker.address().cityName());
        trip.setTimeIn(LocalDateTime.now().plusDays(5).truncatedTo(ChronoUnit.SECONDS));
        trip.setTimeOut(LocalDateTime.now().plusDays(5).plusHours(5).truncatedTo(ChronoUnit.SECONDS));

        session.beginTransaction();
        //Сохранение клиента
        session.save(trip);
        session.getTransaction().commit();
        //Считывание объекта из БД
        TripEntity newTrip = session.get(TripEntity.class, trip.getId());
        System.out.println(newTrip.toString());
    }


}


