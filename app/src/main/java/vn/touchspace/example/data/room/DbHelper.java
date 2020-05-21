package vn.touchspace.example.data.room;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import vn.touchspace.example.data.room.model.User;

/**
 * Created by GNUD on 02/03/2018.
 */

public interface DbHelper {

    Flowable<User> findUser(String nameUser);

    Observable<Boolean> saveUser(User user);

    Observable<List<User>> getAllUsers();
}
