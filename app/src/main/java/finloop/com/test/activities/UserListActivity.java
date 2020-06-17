package finloop.com.test.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import finloop.com.test.R;
import finloop.com.test.interfaces.UserList;
import finloop.com.test.presenters.UserListPresenter;

public class UserListActivity extends AppCompatActivity implements UserList.view, View.OnClickListener {

    @NonNull
    private UserList.presenter presenter;
    @Nullable
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        init();
        initMVP();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);

        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.btn_load).setOnClickListener(this);
    }

    private void initMVP() {
        presenter = new UserListPresenter(this, recyclerView);
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
            case R.id.btn_load:
                presenter.loadUser();
                break;
        }
    }
}
