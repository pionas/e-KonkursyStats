package info.e_konkursy.stats.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.e_konkursy.stats.Adapter.ArticleListAdapter;
import info.e_konkursy.stats.Adapter.UserListAdapter;
import info.e_konkursy.stats.App.App;
import info.e_konkursy.stats.Interface.MainActivityArticleMVP;
import info.e_konkursy.stats.Interface.MainActivityUserMVP;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Model.POJO.User;
import info.e_konkursy.stats.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, MainActivityArticleMVP.View, MainActivityUserMVP.View {

    @BindView(R.id.message)
    TextView mTextMessage;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.container)
    ViewGroup rootView;
    @BindView(R.id.listViewTopArticle)
    RecyclerView listViewTopArticle;
    @BindView(R.id.listViewTopPeople)
    RecyclerView listViewTopPeople;

    @Inject
    MainActivityArticleMVP.Presenter articlePresenter;
    @Inject
    MainActivityUserMVP.Presenter userPresenter;


    private ArticleListAdapter articlesListAdapter;
    private List<Article> articlesList = new ArrayList<>();
    private UserListAdapter usersListAdapter;
    private List<User> usersList = new ArrayList<>();
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        initListener();
        initAdapter();
        initDialog();
        initView();
    }

    private void initListener() {
        navigation.setOnNavigationItemSelectedListener(this);
    }

    private void initAdapter() {
        articlesListAdapter = new ArticleListAdapter(articlesList);
        listViewTopArticle.setAdapter(articlesListAdapter);
        listViewTopArticle.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listViewTopArticle.setItemAnimator(new DefaultItemAnimator());
        listViewTopArticle.setHasFixedSize(true);
        listViewTopArticle.setLayoutManager(new LinearLayoutManager(this));


        usersListAdapter = new UserListAdapter(usersList, userPresenter);
        listViewTopPeople.setAdapter(usersListAdapter);
        listViewTopPeople.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listViewTopPeople.setItemAnimator(new DefaultItemAnimator());
        listViewTopPeople.setHasFixedSize(true);
        listViewTopPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initDialog() {
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(this);
        builderDialog.setMessage(R.string.dialog_loading)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        builderDialog.setCancelable(false);
        dialog = builderDialog.create();
    }

    private void initView() {
        articlePresenter.setView(this);
        articlePresenter.loadData();
        userPresenter.setView(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                changeVisible(View.VISIBLE, View.GONE, View.GONE);
                articlePresenter.loadData();
                return true;
            case R.id.navigation_users:
                changeVisible(View.GONE, View.VISIBLE, View.GONE);
                userPresenter.loadData();
                return true;
            case R.id.navigation_contact:
                changeVisible(View.GONE, View.GONE, View.VISIBLE);
                mTextMessage.setText(R.string.title_contact);
                return true;
        }
        return false;
    }

    private void changeVisible(int article, int people, int message) {
        listViewTopArticle.setVisibility(article);
        listViewTopPeople.setVisibility(people);
        mTextMessage.setVisibility(message);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        articlePresenter.rxUnsubscribe();
//        articlesList.clear();
//        articlesListAdapter.notifyDataSetChanged();
//        userPresenter.rxUnsubscribe();
//        usersList.clear();
//        usersListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSnackbar(String msg) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
    }

    @Override
    public void openUrl(String url) {
        Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void updateData(Article article) {
        articlesList.add(article);
        articlesListAdapter.notifyItemInserted(articlesList.size() - 1);
    }


    @Override
    public void updateData(User user) {
        usersList.add(user);
        usersListAdapter.notifyItemInserted(usersList.size() - 1);
    }
}
