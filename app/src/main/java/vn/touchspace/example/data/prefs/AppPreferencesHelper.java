package vn.touchspace.example.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import vn.touchspace.example.di.ApplicationContext;
import vn.touchspace.example.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by GNUD on 04/12/2017.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final long DEFAULT_VALUE_LONG = -1;
    private static final int DEFAULT_VALUE_INTEGER = -1;
    private static final int DEFAULT_VALUE_FLOAT = -1;

    public static final String PREF_KEY_USERNAME = "PREF_KEY_USERNAME";
    public static final String PREF_KEY_PASSWORD = "PREF_KEY_PASSWORD";
    public static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void save(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    @Override
    public void save(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    @Override
    public void save(String key, float value) {
        mPrefs.edit().putFloat(key, value).apply();
    }

    @Override
    public void save(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
    }

    @Override
    public void save(String key, long value) {
        mPrefs.edit().putLong(key, value).apply();
    }

    @Override
    public boolean getBoolean(String key) {
        return mPrefs.getBoolean(key, false);
    }

    @Override
    public String getString(String key) {
        return mPrefs.getString(key, null);
    }

    @Override
    public long getLong(String key) {
        return mPrefs.getLong(key, DEFAULT_VALUE_LONG);
    }

    @Override
    public int getInt(String key) {
        return mPrefs.getInt(key, DEFAULT_VALUE_INTEGER);
    }

    @Override
    public float getFloat(String key) {
        return mPrefs.getFloat(key, DEFAULT_VALUE_FLOAT);
    }

    @Override
    public void remove(String key) {
        mPrefs.edit().remove(key).apply();
    }
}
