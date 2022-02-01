package repositories;

import config.BaseConnection;
import dictionaries.ServiceEnum;
import org.hibernate.Session;

public class BaseRepository {
    private final ServiceEnum serviceEnum;

    protected BaseRepository(ServiceEnum serviceEnum) {
        this.serviceEnum = serviceEnum;
    }

    protected synchronized Session getSession() {
        return BaseConnection.getInstance().getSession(serviceEnum);
    }

    protected synchronized void closeSession() {
        BaseConnection.getInstance().closeConnection(serviceEnum);
    }

    public synchronized <T> T getById(Class<T> tClass, long id) {
        Session session = getSession();
        T object = session.get(tClass, id);
        closeSession();
        return object;
    }

    public synchronized <T> T create(T object) {
        Session session = getSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        closeSession();
        return object;
    }

    public synchronized <T> void delete(T object) {
        Session session = getSession();
        session.beginTransaction();
        session.remove(object);
        session.getTransaction().commit();
        closeSession();
    }
}
