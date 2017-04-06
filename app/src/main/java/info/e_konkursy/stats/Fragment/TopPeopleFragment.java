package info.e_konkursy.stats.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import info.e_konkursy.stats.Adapter.UserListAdapter;
import info.e_konkursy.stats.App.App;
import info.e_konkursy.stats.Interface.TopPeopleFragmentMVP;
import info.e_konkursy.stats.Model.POJO.User;
import info.e_konkursy.stats.Module.TopPeopleModule;
import info.e_konkursy.stats.R;


public class TopPeopleFragment extends BaseFragment implements TopPeopleFragmentMVP.View {
    @Inject
    TopPeopleFragmentMVP.Presenter presenter;

    private UserListAdapter usersListAdapter;
    private List<User> usersList = new ArrayList<>();

    public TopPeopleFragment() {
    }

    @SuppressWarnings("unused")
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new App().getTopPeopleComponent().topPeopleModule(new TopPeopleModule()).build().inject(this);
        initDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_people_list, container, false);
        ButterKnife.bind(view);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            usersListAdapter = new UserListAdapter(usersList, presenter);
            recyclerView.setAdapter(usersListAdapter);
        }
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public void updateData(User u) {
        usersList.add(u);
        usersListAdapter.notifyItemInserted(usersList.size() - 1);
    }

    @Override
    public void openUrl(String url) {
        Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
