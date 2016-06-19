package by.grodno.toni7777.weather.db;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public interface RealmService {

    <E extends RealmModel> E copyToRealmOrUpdate(Realm realm, final E object);

    void clearDatabase(Realm realm);

    <E extends RealmModel> RealmResults<E> readAll(Realm realm, Class clazz);


    <E extends RealmModel> RealmResults<E> findCity(Realm realm, Class clazz, String city);


    <E extends RealmModel> E findFirstCity(Realm realm, Class clazz, String city);

}
