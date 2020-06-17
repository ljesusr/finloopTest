package finloop.com.test.interactors;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;

import finloop.com.test.R;
import finloop.com.test.activities.UserListActivity;
import finloop.com.test.interfaces.CreateUser;
import finloop.com.test.webServices.ServiceResponse;
import finloop.com.test.webServices.ServiceResponseInterface;
import finloop.com.test.webServices.Services;

import static finloop.com.test.utils.SavedVariables.setJwt;

public class CreateUserInteractor implements CreateUser.interactor, ServiceResponseInterface {

    @Nullable
    private Context context;
    @NonNull
    private CreateUser.presenter presenter;
    @Nullable
    private ProgressDialog loading;

    public CreateUserInteractor(@Nullable Context context, @NonNull CreateUser.presenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.loading = new ProgressDialog(context);
    }

    @Override
    public void makeCreateUserRequest(@NonNull String email, @NonNull String username, @NonNull String password) {
        if (context != null) {
            if (loading != null) {
                loading.setTitle("Validando");
                loading.setMessage("Verificando datos");
                loading.setCancelable(false);
                loading.show();
            }
            new Services().createUser(context, this, email, username, password);
        }
    }

    private void nextRegister(){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom));
        builder.setTitle("Registro exitoso");
        builder.setMessage("¿Que deseas hacer?");
        builder.setCancelable(false);

        builder.setPositiveButton(
                "Ir al listado",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        context.startActivity(new Intent(context, UserListActivity.class));
                        dialog.cancel();
                    }
                });

        builder.setNegativeButton(
                "Registrar nuevo usuario",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void OnSuccessResponse(ServiceResponse response) {
        if (loading != null) {
            loading.dismiss();
        }
        if (response.getErrors() == null) {
            setJwt(context, response.getJwt());
            nextRegister();
        } else {
            if (context != null) {
                presenter.showError(context.getString(R.string.error_service));
            }

        }
    }

    @Override
    public void OnErrorResponse(ServiceResponse response) {
        if (loading != null) {
            loading.dismiss();
        }
        if (context != null) {
            presenter.showError("correo ya registrado");
        }
    }
}
