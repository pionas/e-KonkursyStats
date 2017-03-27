package info.e_konkursy.stats.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import info.e_konkursy.stats.Helpers.StringsHelper;
import info.e_konkursy.stats.Model.POJO.User;
import info.e_konkursy.stats.R;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ListItemViewHolder> {

    private List<User> list;

    public UserListAdapter(List<User> list) {
        this.list = list;
    }

    @Override
    public UserListAdapter.ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new UserListAdapter.ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserListAdapter.ListItemViewHolder holder, int position) {
        holder.userName.setText(StringsHelper.stripslashes(list.get(position).getUsername()));
        holder.userCount.setText(StringsHelper.stripslashes(list.get(position).getIlosc()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView userCount;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.textViewUsername);
            userCount = (TextView) itemView.findViewById(R.id.textViewCount);
        }
    }
}
