package vn.touchspace.example.data.room;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import vn.touchspace.example.data.room.model.User;

/**
 * Created by GNUD on 02/03/2018.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Flowable<User> findUser(String nameUser) {
        return Flowable.fromCallable(()
                -> mAppDatabase.mUserDao().findUser(nameUser));
    }

    @Override
    public Observable<Boolean> saveUser(User user) {
        return Observable.fromCallable(() -> {
            mAppDatabase.mUserDao().saveUser(user);
            return true;
        });
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(()
                -> mAppDatabase.mUserDao().getAllUsers());
    }
}
