package finloop.com.test.interactors;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import finloop.com.test.activities.UserListActivity;
import finloop.com.test.interfaces.Login;
import finloop.com.test.webServices.ServiceResponse;
import finloop.com.test.webServices.ServiceResponseInterface;
import finloop.com.test.webServices.Services;

import static finloop.com.test.utils.SavedVariables.setJwt;

public class LoginInteractor implements Login.interactor, ServiceResponseInterface {
    @Nullable
    private Context context;
    @NonNull
    private Login.presenter presenter;

    public LoginInteractor(@Nullable Context context, @NonNull Login.presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public void makeLoginRequest(@NonNull String email, @NonNull String password) {
        new Services().login(context, this, email, password);
    }

    @Override
    public void OnSuccessResponse(ServiceResponse response) {
        if (response.getErrors() == null) {
            setJwt(context, response.getJwt());
            context.startActivity(new Intent(context, UserListActivity.class));
        } else {
            presenter.showError("Usuario o contraseña incorrectos");
        }
    }

    @Override
    public void OnErrorResponse(ServiceResponse response) {
        presenter.showError("usuario o contraseña incorrectos");
    }
}
