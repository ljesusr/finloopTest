package finloop.com.test.interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface Login {

    interface view {
        void showError(@NonNull String error);// display a toast with a error
    }

    interface presenter {
        void showError(@NonNull String error);// display a toast with a error
        void checkField(@Nullable String email, @Nullable String password);//check field data
    }

    interface interactor {
        void makeLoginRequest(@NonNull String email, @NonNull String password);// make call request
    }
}
