package newsapp.gab.com.newsapp.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import newsapp.gab.com.newsapp.postutilities.Protocols;

/**
 * Created by Gabriel Angelo Inot on 19/03/2018.
 */

public class Post implements Serializable {

    @SerializedName(Protocols.STATUS)
    @Expose
    private String status;
    @SerializedName(Protocols.TOTAL_RESULTS)
    @Expose
    private Integer totalResults;
    @SerializedName(Protocols.ARTICLES)
    @Expose
    private List<Article> articles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
