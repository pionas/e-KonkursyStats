package info.e_konkursy.stats.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.e_konkursy.stats.Helpers.StringsHelper;
import info.e_konkursy.stats.Interface.ListAdapterInterface;
import info.e_konkursy.stats.Interface.TopPeopleFragmentMVP;
import info.e_konkursy.stats.Model.POJO.User;
import info.e_konkursy.stats.R;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ListItemViewHolder> implements ListAdapterInterface {

    private final TopPeopleFragmentMVP.Presenter userPresenter;
    private List<User> list;

    public UserListAdapter(List<User> list, TopPeopleFragmentMVP.Presenter userPresenter) {
        this.list = list;
        this.userPresenter = userPresenter;
    }

    @Override
    public UserListAdapter.ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new UserListAdapter.ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserListAdapter.ListItemViewHolder holder, final int position) {
        holder.userName.setText(StringsHelper.stripslashes(list.get(position).getUsername()));
        holder.userCount.setText(StringsHelper.stripslashes(list.get(position).getIlosc()));
        holder.userRow.setOnClickListener(view -> userPresenter.itemOnClick(list.get(position)));
    }

    @Override
    public void removeAt(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.relativeLayoutUserRow) RelativeLayout userRow;
        @BindView(R.id.textViewUsername) TextView userName;
        @BindView(R.id.textViewCount) TextView userCount;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
