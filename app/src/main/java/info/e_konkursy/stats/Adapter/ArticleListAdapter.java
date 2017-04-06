package info.e_konkursy.stats.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.e_konkursy.stats.Helpers.DateHelper;
import info.e_konkursy.stats.Helpers.StringsHelper;
import info.e_konkursy.stats.Interface.HomeFragmentMVP;
import info.e_konkursy.stats.Interface.ListAdapterInterface;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Utils.Environment;
import info.e_konkursy.stats.Utils.ImageLoader;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ListItemViewHolder> implements ListAdapterInterface {

    private final Context context;
    private final HomeFragmentMVP.Presenter presenter;
    private List<Article> list;

    public ArticleListAdapter(List<Article> list, Context context, HomeFragmentMVP.Presenter presenter) {
        this.list = list;
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_row, parent, false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        holder.articleName.setText(StringsHelper.stripslashes(list.get(position).getTitle()));
        holder.categoryName.setText(StringsHelper.stripslashes(list.get(position).getCategoryName()));
        holder.linearLayoutArticleRow.setOnClickListener(view -> presenter.itemOnClick(list.get(position)));
        if (list.get(position).getImage() != null && !list.get(position).getImage().isEmpty()) {
            String url = DateHelper.changeFormatDate("yyyy/MM/dd/", list.get(position).getDateAdd());
            String imageUri = Environment.baseArticleImageUrl + url + list.get(position).getImage();
            holder.imageView.setVisibility(View.VISIBLE);
            ImageLoader.loadImage(context, imageUri, holder.imageView);
        } else {
            holder.imageView.setVisibility(View.GONE);
        }
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

    static class ListItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.linearLayoutArticleRow)
        LinearLayout linearLayoutArticleRow;
        @BindView(R.id.textViewArticleTitle)
        TextView articleName;
        @BindView(R.id.textViewArticleCategoryName)
        TextView categoryName;
        @BindView(R.id.imageViewArticle)
        ImageView imageView;

        ListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
