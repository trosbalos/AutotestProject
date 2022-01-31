package repositories;

import config.BaseConnection;
import dictionaries.ServiceEnum;
import org.hibernate.Session;

public class BaseRepository {
    private ServiceEnum serviceEnum;

    protected BaseRepository(ServiceEnum serviceEnum) {
        this.serviceEnum = serviceEnum;
    }

    protected Session getSession() {
        return BaseConnection.getInstance().getSession(serviceEnum);
    }

    protected void closeSession() {
        BaseConnection.getInstance().closeConnection(serviceEnum);
    }

    public <T> T getById(Class<T> tClass, long id) {
        Session session = getSession();
        T object = session.get(tClass, id);
        closeSession();
        return object;
    }
    public <T> T create(T object) {
        Session session = getSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        closeSession();
        return object;
    }
}
