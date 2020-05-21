/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package vn.touchspace.example.di.module;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;

import com.touchspace.example.R;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import vn.touchspace.example.data.AppDataManager;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.ApiService;
import vn.touchspace.example.data.network.AppApiHelper;
import vn.touchspace.example.data.prefs.AppPreferencesHelper;
import vn.touchspace.example.data.prefs.PreferencesHelper;
import vn.touchspace.example.data.realm.AppRealmHelper;
import vn.touchspace.example.data.realm.RealmHelper;
import vn.touchspace.example.data.room.AppDatabase;
import vn.touchspace.example.data.room.AppDbHelper;
import vn.touchspace.example.data.room.DbHelper;
import vn.touchspace.example.di.ApplicationContext;
import vn.touchspace.example.di.DatabaseInfo;
import vn.touchspace.example.di.PreferenceInfo;
import vn.touchspace.example.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by GNUD on 27/01/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiService provideApiService(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    RealmHelper provideRealmHelper(AppRealmHelper appRealmHelper) {
        return appRealmHelper;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseInfo() {
        return AppDatabase.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@ApplicationContext Context context, @DatabaseInfo String dbName) {
        return AppDatabase.getInstance(context, dbName);
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    NotificationManager provideNotificationManager(@ApplicationContext Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

}
