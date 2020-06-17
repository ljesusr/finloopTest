package finloop.com.test.interfaces;

import androidx.annotation.NonNull;

public interface UserList {
    interface view {
        void showError(@NonNull String error);// display a toast with a error
    }

    interface presenter {
        void showError(@NonNull String error);// display a toast with a error
        void loadUser();
    }

    interface interactor {
        void makeGetUserListRequest();// make call request
    }
}
