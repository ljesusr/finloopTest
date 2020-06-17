package finloop.com.test.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import finloop.com.test.R;

public class UserDetailActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        init();
    }

    private void init() {
        TextView email = findViewById(R.id.tv_email);
        TextView username = findViewById(R.id.tv_username);

        email.setText("Email: " + getIntent().getStringExtra("email"));
        username.setText("Usuario: " + getIntent().getStringExtra("username"));

        findViewById(R.id.btn_back).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            finish();
        }
    }
}
