package finloop.com.test.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import finloop.com.test.R;
import finloop.com.test.interfaces.CreateUser;
import finloop.com.test.presenters.CreateUserPresenter;

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener, CreateUser.view {

    @Nullable
    EditText email, username, password, passwordConfirm;
    @NonNull
    private CreateUser.presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        init();
        initMVP();
    }

    private void init() {
        email = findViewById(R.id.et_email);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        passwordConfirm = findViewById(R.id.et_password_confirm);

        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_clean).setOnClickListener(this);
    }

    private void initMVP() {
        presenter = new CreateUserPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_save:
                presenter.checkField(email.getText().toString(), username.getText().toString(),
                        password.getText().toString(), passwordConfirm.getText().toString());
                break;
            case R.id.btn_clean:
                if(email != null)
                    email.setText("");
                if(username != null)
                    username.setText("");
                if(password != null)
                    password.setText("");
                if(passwordConfirm!= null)
                    passwordConfirm.setText("");
                break;
        }
    }

    @Override
    public void showError(@NonNull String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
