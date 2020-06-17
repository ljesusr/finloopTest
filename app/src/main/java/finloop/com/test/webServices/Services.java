package finloop.com.test.webServices;

import android.content.Context;

import androidx.annotation.NonNull;

import finloop.com.test.R;

public class Services {
    @NonNull
    private VolleyRequests requests = new VolleyRequests();

    public void login(@NonNull final Context context, @NonNull final ServiceResponseInterface serviceResponseInterface, @NonNull final String email, @NonNull final String password) {
        requests.login(context,serviceResponseInterface,context.getString(R.string.endpoint_login), email, password);
    }

    public void createUser(@NonNull final Context context, @NonNull final ServiceResponseInterface serviceResponseInterface, @NonNull final String email, @NonNull final String username, @NonNull final String password) {
        requests.createUser(context,serviceResponseInterface,context.getString(R.string.endpoint_createUser), email, username, password);
    }

    public void getUserList(@NonNull final Context context, @NonNull final ServiceResponseInterface serviceResponseInterface) {
        requests.getUserList(context, serviceResponseInterface, context.getString(R.string.endpoint_createUser));
    }
}
