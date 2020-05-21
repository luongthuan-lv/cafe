package vn.touchspace.example.utils;

import android.annotation.SuppressLint;
import android.graphics.Paint;
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
            double d = Double.valueOf(correctNumber(number));
            return formatter.format(d);
        } catch (Exception e) {
            return number;
        }
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

