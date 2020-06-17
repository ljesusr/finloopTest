package finloop.com.test.presenters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import finloop.com.test.R;
import finloop.com.test.activities.LoginActivity;
import finloop.com.test.interactors.LoginInteractor;
import finloop.com.test.interfaces.Login;

public class LoginPresenter implements Login.presenter {
    @Nullable
    private Context context;
    @Nullable
    private Login.view view;
    @Nullable
    private Login.interactor interactor;

    public LoginPresenter(@Nullable Login.view view) {
        this.view = view;
        this.context = (LoginActivity)view;
        this.interactor = new LoginInteractor(context, this);
    }

    @Override
    public void showError(@NonNull String error) {
        if (view != null) {
            view.showError(error);
        }
    }

    @Override
    public void checkField(@Nullable String email, @Nullable String password) {
        if (email == null || password == null) {
            showError(context.getString(R.string.error_miss_field));
        } else {
            if (email.isEmpty() || password.isEmpty()) {
                showError(context.getString(R.string.error_miss_field));
            } else {
                interactor.makeLoginRequest(email, password);
            }
        }
    }
}
