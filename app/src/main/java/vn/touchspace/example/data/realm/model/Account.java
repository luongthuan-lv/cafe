package vn.touchspace.example.data.realm.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by GNUD on 05/12/2017.
 */

public class Account extends RealmObject {

    @PrimaryKey
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
