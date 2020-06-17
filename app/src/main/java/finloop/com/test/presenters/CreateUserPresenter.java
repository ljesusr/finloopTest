package finloop.com.test.presenters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.regex.Pattern;

import finloop.com.test.R;
import finloop.com.test.activities.CreateUserActivity;
import finloop.com.test.interactors.CreateUserInteractor;
import finloop.com.test.interfaces.CreateUser;

public class CreateUserPresenter implements CreateUser.presenter {
    @Nullable
    private Context context;

    @Nullable
    private CreateUser.view view;
    @NonNull
    private CreateUser.interactor interactor;

    public CreateUserPresenter(@Nullable CreateUser.view view) {
        this.view = view;
        this.context = (CreateUserActivity)view;
        this.interactor = new CreateUserInteractor(context, this);
    }

    @Override
    public void showError(@NonNull String error) {
        if (view != null) {
            view.showError(error);
        }
    }

    @Override
    public boolean isValidEmailId(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    @Override
    public void checkField(@Nullable String email, @Nullable String username, @Nullable String password, @Nullable String passwordConfirm) {
        if (password.equals(passwordConfirm)) {
            if (email.isEmpty() || username.isEmpty()) {
                if (context != null)
                    showError(context.getString(R.string.error_miss_field));
            } else {
                if (this.isValidEmailId(email)) {
                    interactor.makeCreateUserRequest(email, username, password);
                } else {
                    if (context != null)
                        showError(context.getString(R.string.error_email));
                }

            }
        } else {
            if (context != null)
                showError(context.getString(R.string.error_password_match));
        }
    }
}
