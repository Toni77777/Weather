package by.grodno.toni7777.weather.db;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class DBService implements RealmService {

    private static DBService mInstance = null;

    private DBService() {
    }

    public static DBService getInstance() {
        if (mInstance == null) {
            mInstance = new DBService();
        }
        return mInstance;
    }

    public <E extends RealmModel> E copyToRealmOrUpdate(Realm realm, final E object) {
        realm.executeTransactionAsync(realm1 -> realm1.copyToRealmOrUpdate(object));
        return object;
    }

    public void clearDatabase(Realm realm) {
        realm.executeTransaction(realm1 -> realm1.deleteAll());
    }

    public <E extends RealmModel> RealmResults<E> readAll(Realm realm, Class clazz) {
        RealmResults<E> result = realm.where(clazz)
                .findAll();
        return (RealmResults<E>) result;
    }

    public <E extends RealmModel> RealmResults<E> findCity(Realm realm, Class clazz, String city) {
        RealmResults<E> result = realm.where(clazz)
                .contains("city", city)
                .findAll();
        return (RealmResults<E>) result;
    }

    public <E extends RealmModel> E findFirstCity(Realm realm, Class clazz, String city) {
        RealmResults<E> result = findCity(realm, clazz, city);
        return result.first();
    }

}
