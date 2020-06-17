package finloop.com.test.adapters.handlers;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import finloop.com.test.activities.UserDetailActivity;
import finloop.com.test.models.User;

public class UserListHandler {
    @Nullable
    private Context context;

    public UserListHandler(@Nullable Context context) {
        this.context = context;
    }

    public void onSelectUser(User user) {
        Intent userDetail = new Intent(context, UserDetailActivity.class);
        userDetail.putExtra("email", user.getEmail());
        userDetail.putExtra("username", user.getUsername());
        context.startActivity(userDetail);
    }
}
