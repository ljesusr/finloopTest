package finloop.com.test.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static android.content.Context.MODE_PRIVATE;

public class SavedVariables {

    public static void setJwt(@Nullable Context context, @NonNull String jwt) {
        if (context != null) {
            SharedPreferences.Editor editor = context.getSharedPreferences("token", MODE_PRIVATE).edit();
            editor.putString("jwt", jwt);
            editor.apply();
        }
    }

    public static String getJwt(@Nullable Context context) {
        String jwt = "";
        if (context != null) {
            SharedPreferences prefs = context.getSharedPreferences("token", MODE_PRIVATE);
            jwt = prefs.getString("jwt", "");
        }
        return jwt;
    }
}
