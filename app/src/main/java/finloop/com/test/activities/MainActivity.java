package finloop.com.test.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import finloop.com.test.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        findViewById(R.id.btn_create_user).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_user:
                startActivity(new Intent(this, CreateUserActivity.class));
                break;

            case R.id.btn_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
