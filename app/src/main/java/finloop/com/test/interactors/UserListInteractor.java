package finloop.com.test.interactors;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import finloop.com.test.R;
import finloop.com.test.adapters.UserListAdapter;
import finloop.com.test.interfaces.UserList;
import finloop.com.test.webServices.ServiceResponse;
import finloop.com.test.webServices.ServiceResponseInterface;
import finloop.com.test.webServices.Services;

public class UserListInteractor implements UserList.interactor, ServiceResponseInterface {
    @Nullable
    private Context context;
    @NonNull
    private UserList.presenter presenter;
    @Nullable
    private RecyclerView recyclerView;
    @Nullable
    private ProgressDialog loading;

    public UserListInteractor(@Nullable Context context, @NonNull UserList.presenter presenter, @Nullable RecyclerView recyclerView) {
        this.context = context;
        this.presenter = presenter;
        this.recyclerView = recyclerView;
        this.loading = new ProgressDialog(context);
        makeGetUserListRequest();
    }

    @Override
    public void makeGetUserListRequest() {
        if (context != null) {
            if (loading != null) {
                loading.setTitle("Validando");
                loading.setMessage("Cargando usuarios");
                loading.setCancelable(false);
                loading.show();
            }
            new Services().getUserList(context, this);
        }

    }

    @Override
    public void OnSuccessResponse(ServiceResponse response) {
        if (loading != null) {
            loading.dismiss();
        }
        if (response.getErrors() == null) {
            LinearLayoutManager layoutManager  = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(new UserListAdapter(response.getUsers(), context));
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
            presenter.showError(context.getString(R.string.error_service));
        }
    }
}
