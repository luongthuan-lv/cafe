package vn.touchspace.example.utils;

import android.text.TextUtils;

/**
 * Created by GNUD on 19/12/2017.
 */

public class ValidateUtils {

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
