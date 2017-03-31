package info.e_konkursy.stats.Adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import info.e_konkursy.stats.App.App;
import info.e_konkursy.stats.Helpers.StringsHelper;
import info.e_konkursy.stats.Interface.ListAdapterInterface;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.R;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ListItemViewHolder> implements ListAdapterInterface {

    private List<Article> list;

    public ArticleListAdapter(List<Article> list) {
        this.list = list;
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
        if (list.get(position).getImage() != null && !list.get(position).getImage().isEmpty()) {
            File file = new File(new App().getStoragePath() + File.separator + list.get(position).getImage());
            Uri uri = Uri.fromFile(file);
            holder.imageView.setImageURI(uri);
            holder.imageView.setVisibility(View.VISIBLE);
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

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {
        public TextView articleName;
        public TextView categoryName;
        public ImageView imageView;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            articleName = (TextView) itemView.findViewById(R.id.textViewArticleTitle);
            categoryName = (TextView) itemView.findViewById(R.id.textViewArticleCategoryName);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewArticle);
        }
    }
}
