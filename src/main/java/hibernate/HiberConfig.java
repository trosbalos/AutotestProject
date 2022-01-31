package hibernate;

import com.github.javafaker.Faker;
import com.sun.xml.bind.v2.util.QNameMap;
import groovy.lang.MapWithDefault;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.postgresql.gss.GSSOutputStream;
import tripDemo.model.Trip;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class HiberConfig {
//    int a;
//
//    public HiberConfig(int a) {
//        this.a = a;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        HiberConfig that = (HiberConfig) o;
//
//        return a == that.a;
//    }
//
//    @Override
//    public int hashCode() {
//        return 1;
//    }
//
//    public static void main(String[] args) {
//
//        HiberConfig ab1 = new HiberConfig (10);
//        HiberConfig ab2 = new HiberConfig (20);
//        System.out.println(ab1.equals(ab2));
//        System.out.println(ab1.hashCode());
//        System.out.println(ab2.hashCode());
//
//        Map<HiberConfig,String> map = new HashMap<>();
//        map.put(ab1,"ab1obj");
//        map.put(ab2,"ab2obj");
//        System.out.println(map.size());
//
//        Iterator<Map.Entry<HiberConfig, String>> itr = map.entrySet().iterator();
//        while(itr.hasNext()) {
//            Map.Entry<HiberConfig, String> entry =  itr.next();
//            // get key
//            HiberConfig key = entry.getKey();
//            System.out.println(key.a);
//            // get value
//            String value = entry.getValue();
//            System.out.println(value);
//        }

//
//        Faker faker = new Faker();
//
//        Configuration configuration = new Configuration();
//        configuration.configure();
//        configuration.addAnnotatedClass(TripEntity.class);
//        configuration.addAnnotatedClass(CompanyEntity.class);
//        configuration.addAnnotatedClass(PassengerEntity.class);
//
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties()).build();
//        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//
//        Session session = sessionFactory.openSession();
//
//        //Генерация тестовой модели
//        TripEntity trip = new TripEntity();
//        PassengerEntity passenger = new PassengerEntity ();
//        passenger.setFirstName(faker.address().firstName());
//        passenger.setMiddleName(faker.name().firstName());
//        passenger.setLastName(faker.address().lastName());
//
//        PassengerEntity passenger2 = new PassengerEntity ();
//        passenger2.setFirstName(faker.address().firstName());
//        passenger2.setMiddleName(faker.name().firstName());
//        passenger2.setLastName(faker.address().lastName());
//
//        trip.getPassengerList().add(passenger);
//        trip.getPassengerList().add(passenger2);
//
//        CompanyEntity company = new CompanyEntity ();
//        company.setId(1L);
//        company.setName(faker.company().name());
//        company.setOpenDate(LocalDateTime.now().plusDays(5).truncatedTo(ChronoUnit.SECONDS).toLocalDate());
//
//        trip.setCompany(company);
//        trip.setPlane(faker.cat().name());
//        trip.setTownFrom(faker.address().cityName());
//        trip.setTownTo(faker.address().cityName());
//        trip.setTimeIn(LocalDateTime.now().plusDays(5).truncatedTo(ChronoUnit.SECONDS));
//        trip.setTimeOut(LocalDateTime.now().plusDays(5).plusHours(5).truncatedTo(ChronoUnit.SECONDS));
//
//
//        session.beginTransaction();
//        //Сохранение клиента
//        session.save(trip);
//        session.getTransaction().commit();
//        //Считывание объекта из БД
//        TripEntity newTrip = session.get(TripEntity.class, trip.getId());
//        System.out.println(newTrip.toString());

//    }
}

