package vn.touchspace.example.data.room.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import vn.touchspace.example.data.room.model.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE name LIKE :name LIMIT 1")
    User findUser(String name);

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUser(User user);
}
