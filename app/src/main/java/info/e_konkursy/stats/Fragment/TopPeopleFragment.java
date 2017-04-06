package info.e_konkursy.stats.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
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
    public static TopPeopleFragment newInstance() {
        TopPeopleFragment fragment = new TopPeopleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new App().getTopPeopleComponent().topPeopleModule(new TopPeopleModule()).build().inject(this);
        initDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_top_people_list, container, false);
        ButterKnife.bind(this, rootView);
        // Set the adapter
        if (rootView instanceof RecyclerView) {
            Context context = rootView.getContext();
            RecyclerView recyclerView = (RecyclerView) rootView;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
            usersListAdapter = new UserListAdapter(usersList, presenter);
            recyclerView.setAdapter(usersListAdapter);
        }
        return rootView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        if (usersList == null || usersList.size() == 0) {
            presenter.loadData();
        }
    }

    @Override
    public void updateData(User u) {
        usersList.add(u);
        usersListAdapter.notifyItemInserted(usersList.size() - 1);
    }

}
