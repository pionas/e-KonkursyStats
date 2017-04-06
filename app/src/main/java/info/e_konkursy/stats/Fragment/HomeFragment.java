package info.e_konkursy.stats.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import info.e_konkursy.stats.Adapter.ArticleListAdapter;
import info.e_konkursy.stats.App.App;
import info.e_konkursy.stats.Interface.HomeFragmentMVP;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Module.HomeModule;
import info.e_konkursy.stats.R;


public class HomeFragment extends BaseFragment implements HomeFragmentMVP.View {

    @Inject
    HomeFragmentMVP.Presenter presenter;

    private ArticleListAdapter articlesListAdapter;
    private List<Article> articlesList = new ArrayList<>();

    public HomeFragment() {
    }

    @SuppressWarnings("unused")
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new App().getHomeComponent().homeModule(new HomeModule()).build().inject(this);
        initDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);
        ButterKnife.bind(view);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            articlesListAdapter = new ArticleListAdapter(articlesList);
            recyclerView.setAdapter(articlesListAdapter);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public void updateData(Article a) {
        articlesList.add(a);
        articlesListAdapter.notifyItemInserted(articlesList.size() - 1);
    }
}
