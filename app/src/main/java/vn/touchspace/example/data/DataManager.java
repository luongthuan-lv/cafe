package vn.touchspace.example.data;

import vn.touchspace.example.data.network.ApiService;
import vn.touchspace.example.data.prefs.PreferencesHelper;
import vn.touchspace.example.data.realm.RealmHelper;
import vn.touchspace.example.data.room.DbHelper;

/**
 * Created by GNUD on 02/12/2017.
 */

public interface DataManager extends ApiService, PreferencesHelper, RealmHelper, DbHelper {

}
