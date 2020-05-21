package vn.touchspace.example.service.notify;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;

import com.touchspace.example.R;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.realm.model.Account;
import vn.touchspace.example.data.room.model.User;
import vn.touchspace.example.di.component.DaggerServiceComponent;
import vn.touchspace.example.di.component.ServiceComponent;
import vn.touchspace.example.di.module.ActivityModule;
import vn.touchspace.example.di.module.ServiceModule;
import vn.touchspace.example.manager.MvpApp;
import vn.touchspace.example.ui.splash.SplashActivity;
import vn.touchspace.example.utils.rx.SchedulerProvider;

import static vn.touchspace.example.utils.AppConstants.PRIMARY_CHANNEL;

public class NotifyService extends Service {

    private static final String TAG = NotifyService.class.getSimpleName();
    public final int mId = 12345;
    public PendingIntent pendingIntent;

    @Inject
    DataManager mDataManager;

    @Inject
    CompositeDisposable mCompositeDisposable;

    @Inject
    SchedulerProvider mSchedulerProvider;

    @Inject
    NotificationManager mNotificationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        onRebind(intent);
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceComponent component = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(this))
                .applicationComponent(((MvpApp) getApplication()).getComponent())
                .build();
        component.inject(this);

        Log.i(TAG, "Service notification daily is running");
        try {
            Account account = mDataManager.findFirst(Account.class);
            if (account != null) {
                Log.i(TAG, "onCreate: " + account.username + "\n" + account.password);
            }
            mDataManager.getAllUsers();
            mCompositeDisposable.add(mDataManager.getAllUsers()
                    .subscribeOn(mSchedulerProvider.io())
                    .observeOn(mSchedulerProvider.ui())
                    .subscribe(users -> {
                        for (User userItem : users) {
                            Log.i(TAG, "user: " + userItem.getName());
                        }
                    }, throwable -> {
                        Log.i(TAG, "throwable: " + throwable.getMessage());
                    }));
            createNotification(getApplicationContext());
            setNextAlarm(getApplicationContext(), true, 0, 0);
            stopSelf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNotification(Context context) {
        Drawable myDrawable = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            myDrawable = getResources().getDrawable(R.mipmap.ic_launcher, null);
        } else {
            myDrawable = getResources().getDrawable(R.mipmap.ic_launcher);
        }
        Bitmap anImage = ((BitmapDrawable) myDrawable).getBitmap();
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, PRIMARY_CHANNEL)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(anImage)
                        .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(getString(R.string.text_notify_sample))
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, SplashActivity.class);
        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(SplashActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        //mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        try {
            mNotificationManager.notify(mId, mBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNextAlarm(Context context, boolean isToday, int hour, int minutes) {
        Intent myIntent = new Intent(context, NotifyService.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pendingIntent = PendingIntent.getService(context, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.cancel(pendingIntent);

        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, hour);
//        calendar.set(Calendar.MINUTE, minutes);
//        calendar.set(Calendar.SECOND, 0);
        long time;
        if (isToday) { // ngày hôm nay
            time = calendar.getTimeInMillis() + 5 * 1000;
        } else {
            time = calendar.getTimeInMillis() + 24 * 60 * 60 * 1000; // 1 ngày sau
        }
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }


    public void cancelNotification(Context context) {
        Intent myIntent = new Intent(context, NotifyService.class);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        assert alarmManager != null;
        alarmManager.cancel(pendingIntent);
    }

}
