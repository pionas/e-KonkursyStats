package info.e_konkursy.stats.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

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
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


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
        setRetainInstance(true);
        new App().getHomeComponent().homeModule(new HomeModule()).build().inject(this);
        initDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home_list, container, false);
        ButterKnife.bind(this, rootView);
        // Set the adapter
        if (rootView instanceof RecyclerView) {
            Context context = rootView.getContext();
            RecyclerView recyclerView = (RecyclerView) rootView;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
            articlesListAdapter = new ArticleListAdapter(articlesList, context, presenter);

            AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(articlesListAdapter);
            alphaAdapter.setFirstOnly(false);
            alphaAdapter.setInterpolator(new OvershootInterpolator());
            recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        if (articlesList == null || articlesList.size() == 0) {
            presenter.loadData();
        }
    }

    @Override
    public void updateData(Article a) {
        articlesList.add(a);
        articlesListAdapter.notifyItemInserted(articlesList.size() - 1);
    }

}
