package vn.touchspace.example.utils;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GNUD on 21/12/2017.
 */

public class AppUtils {

    public static String formatNumber(String number) {
        DecimalFormat formatter = new DecimalFormat("###,###.###");
        try {
            double d = Double.parseDouble(correctNumber(number));
            return formatter.format(d);
        } catch (Exception e) {
            return number;
        }
    }

    public static String formatNumber(double number) {
        DecimalFormat formatter = new DecimalFormat("###,###.###");
            return formatter.format(number);
    }

    public static String convertTime(long time) {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(time);

    }

    public static String getRole(String role){
        if(role.equals("director")){
            return "Giám đốc";
        }else if (role.equals("manager")){
            return "Quản lý";
        }else {
            return "Nhân viên";
        }
    }

    public static void isDoubleClick(View view) {
        view.setEnabled(false);
        new Handler().postDelayed(() -> view.setEnabled(true), 500);
    }

    public static void setDefaultAvatar(TextView view, String username) {
        //set avatar bằng các chữ cái đầu
        String nameSet = "";
        String[] name = username.split(" ");
        for (int i = 0; i < name.length; i++) {
            String s = name[i];
            nameSet = nameSet + s.charAt(0);
        }
        if (nameSet.length() > 1) {
            nameSet = nameSet.substring(nameSet.length() - 2);
        }
        view.setText(nameSet);
        view.setVisibility(View.VISIBLE);
    }

    private static String correctNumber(String number) {
        return number.replaceAll("[\\D]", "");
    }

    public static void setUnderLineTextView(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public static String convertTime(String time) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatterInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatterInput.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        if (date == null) {
            return time;
        }
        return formatter.format(date);
    }
}

