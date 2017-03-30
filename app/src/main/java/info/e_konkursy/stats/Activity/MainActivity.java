package info.e_konkursy.stats.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.e_konkursy.stats.Adapter.ArticleListAdapter;
import info.e_konkursy.stats.Adapter.UserListAdapter;
import info.e_konkursy.stats.App.App;
import info.e_konkursy.stats.Helpers.PermissionsHelper;
import info.e_konkursy.stats.Interface.MainActivityMVP;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Model.POJO.User;
import info.e_konkursy.stats.Module.StatsModule;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Utils.Constants;
import info.e_konkursy.stats.Utils.DialogManager;
import info.e_konkursy.stats.Validators.ContactValidator;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, MainActivityMVP.View {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.container)
    ViewGroup rootView;
    @BindView(R.id.listViewTopArticle)
    RecyclerView listViewTopArticle;
    @BindView(R.id.listViewTopPeople)
    RecyclerView listViewTopPeople;
    @BindView(R.id.linearLayoutContact)
    LinearLayout linearLayoutContact;
    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.editTextMail)
    EditText editTextMail;
    @BindView(R.id.editTextMessage)
    EditText editTextMessage;

    @Inject
    MainActivityMVP.Presenter presenter;
    @Inject
    ContactValidator contactValidator;


    private ArticleListAdapter articlesListAdapter;
    private List<Article> articlesList = new ArrayList<>();
    private UserListAdapter usersListAdapter;
    private List<User> usersList = new ArrayList<>();
    private AlertDialog dialog;
    private ArrayList<String> persmissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().statsModule(new StatsModule(this)).build().inject(this);

        ButterKnife.bind(this);

        persmissions = PermissionsHelper.getPermissionList();

        initListener();
        initAdapter();
        initDialog();
        if (!requestPermissions()) {
            return;
        }
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

        usersListAdapter = new UserListAdapter(usersList, presenter);
        listViewTopPeople.setAdapter(usersListAdapter);
        listViewTopPeople.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listViewTopPeople.setItemAnimator(new DefaultItemAnimator());
        listViewTopPeople.setHasFixedSize(true);
        listViewTopPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initDialog() {
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(this);
        builderDialog.setMessage(R.string.dialog_loading)
                .setNegativeButton(R.string.button_cancel, (dialog1, id) -> hideDialog());
        builderDialog.setCancelable(false);
        dialog = builderDialog.create();
    }

    private void initView() {
        presenter.setView(this);
//        presenter.loadArticleData();
    }

    public void initValidate(View view) {
        contactValidator.validate();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                changeVisible(View.VISIBLE, View.GONE, View.GONE);
                articlesList.clear();
                articlesListAdapter.notifyDataSetChanged();
                presenter.loadArticleData();
                return true;
            case R.id.navigation_users:
                changeVisible(View.GONE, View.VISIBLE, View.GONE);
                usersList.clear();
                usersListAdapter.notifyDataSetChanged();
                presenter.loadUserData();
                return true;
            case R.id.navigation_contact:
                changeVisible(View.GONE, View.GONE, View.VISIBLE);
                return true;
        }
        return false;
    }

    private void changeVisible(int article, int people, int message) {
        listViewTopArticle.setVisibility(article);
        listViewTopPeople.setVisibility(people);
        linearLayoutContact.setVisibility(message);
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
    public MainActivity getActivity() {
        return this;
    }

    @Override
    public void updateData(Object o) {
        if (o instanceof User) {
            usersList.add((User) o);
            usersListAdapter.notifyItemInserted(usersList.size() - 1);
        }
        if (o instanceof Article) {
            articlesList.add((Article) o);
            articlesListAdapter.notifyItemInserted(articlesList.size() - 1);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
    }

    private boolean requestPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        List<String> listPermissionsNeeded = new ArrayList<>();

        for (String persmission : persmissions) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), persmission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(persmission);
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), Constants.REQUEST_CODE_ASK_PERMISSIONS);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_ASK_PERMISSIONS:
                Map<String, Integer> perms = new HashMap<>();

                for (String persmission : persmissions) {
                    perms.put(persmission, PackageManager.PERMISSION_GRANTED);
                }

                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++) {
                        perms.put(permissions[i], grantResults[i]);
                    }

                    boolean startActivity = true;
                    for (String persmission : persmissions) {
                        if (perms.get(persmission) != PackageManager.PERMISSION_GRANTED) {
                            startActivity = false;
                        }
                    }
                    if (startActivity) {
                        initView();
                    } else {

                        boolean showDialog = false;
                        for (String persmission : persmissions) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, persmission)) {
                                showDialog = true;
                            }
                        }

                        if (showDialog) {
                            DialogManager.showPermissionsDialog(
                                    MainActivity.this,
                                    (dialogInterface, i) -> requestPermissions()
                            );
                        } else {
                            showSnackbar(getString(R.string.app_permissions));
                        }
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
