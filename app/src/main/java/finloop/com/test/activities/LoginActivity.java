package finloop.com.test.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import finloop.com.test.R;
import finloop.com.test.interfaces.Login;
import finloop.com.test.presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements Login.view, View.OnClickListener {

    @Nullable
    private EditText email, password;
    @Nullable
    private Login.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initMVP();
    }

    private void init() {
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);

        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    private void initMVP() {
        presenter = new LoginPresenter(this);
    }

    @Override
    public void showError(@NonNull String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_login:
                presenter.checkField(email.getText().toString(), password.getText().toString());
                break;
        }
    }
}
