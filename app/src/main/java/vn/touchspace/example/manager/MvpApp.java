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

package vn.touchspace.example.manager;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import com.touchspace.example.R;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.AppApiHelper;
import vn.touchspace.example.di.component.ApplicationComponent;
import vn.touchspace.example.di.component.DaggerApplicationComponent;
import vn.touchspace.example.di.module.ApplicationModule;
import vn.touchspace.example.service.notify.NotifyService;
import vn.touchspace.example.utils.AppConstants;
import vn.touchspace.example.utils.AppLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;



/**
 * Created by GNUD on 27/01/17.
 */

public class MvpApp extends Application {

    private static final String TAG = MvpApp.class.getSimpleName();

    @Inject
    DataManager mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    @Inject
    NotificationManager mNotificationManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();
        printHashKey(this.getPackageName());
        AppApiHelper.init();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mNotificationChannel = new NotificationChannel(AppConstants.PRIMARY_CHANNEL,
                    getString(R.string.text_notify_channel_sample), NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationChannel.setLightColor(Color.GREEN);
            mNotificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            mNotificationManager.createNotificationChannel(mNotificationChannel);
            Log.i(TAG, "onCreate: Android O");
        }
        //CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public void printHashKey(String packageName) {
        // Add code to print out the key hash
        try {
            @SuppressLint("PackageManagerGetSignatures")
            PackageInfo info = getPackageManager().getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e(TAG, "KeyHash:" + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
