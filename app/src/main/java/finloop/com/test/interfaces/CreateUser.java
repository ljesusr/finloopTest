package finloop.com.test.interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface CreateUser {
    interface view {
        void showError(@NonNull String error);// display a toast with a error
    }

    interface presenter {
        void showError(@NonNull String error);// display a toast with a error
        boolean isValidEmailId(String email);
        void checkField(@Nullable String email, @Nullable String username, @Nullable String password, @Nullable String passwordConfirm);//check field data
    }

    interface interactor {
        void makeCreateUserRequest(@NonNull String email, @NonNull String username, @NonNull String password);// make call request
    }
}
