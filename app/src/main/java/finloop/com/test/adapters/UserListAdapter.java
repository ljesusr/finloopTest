package finloop.com.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import finloop.com.test.R;
import finloop.com.test.adapters.handlers.UserListHandler;
import finloop.com.test.databinding.ItemUserBinding;
import finloop.com.test.models.User;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.BindingHolder> {

    private Context context;
    private List<User> userList;

    public UserListAdapter(List<User> userList, @Nullable Context context) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        BindingHolder holder = new BindingHolder(v);
        ItemUserBinding binding = DataBindingUtil.getBinding(v);
        binding.setHandler(new UserListHandler(context));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        final User user= userList.get(position);
        holder.getBinding().setVariable(BR.user, user);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        BindingHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        ViewDataBinding getBinding() {
            return binding;
        }
    }
}