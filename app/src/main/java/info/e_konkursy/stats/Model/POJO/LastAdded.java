package info.e_konkursy.stats.Model.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class LastAdded {

    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("articleList")
    @Expose
    private List<Article> articles = null;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> article) {
        this.articles = article;
    }

}