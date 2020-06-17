package finloop.com.test.presenters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import finloop.com.test.activities.UserListActivity;
import finloop.com.test.interactors.UserListInteractor;
import finloop.com.test.interfaces.UserList;

public class UserListPresenter implements UserList.presenter {
    @Nullable
    private UserList.view view;
    @NonNull
    private UserList.interactor interactor;

    public UserListPresenter(@Nullable UserList.view view, @Nullable RecyclerView recyclerView) {
        this.view = view;
        Context context = (UserListActivity) view;
        this.interactor = new UserListInteractor(context, this, recyclerView);
    }


    @Override
    public void showError(@NonNull String error) {
        if (view != null) {
            view.showError(error);
        }
    }

    @Override
    public void loadUser() {
        interactor.makeGetUserListRequest();
    }
}
