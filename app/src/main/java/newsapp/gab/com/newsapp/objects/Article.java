package newsapp.gab.com.newsapp.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import newsapp.gab.com.newsapp.postutilities.Protocols;

/**
 * Created by gabriel on 19/03/2018.
 */

public class Article implements Serializable {

    @SerializedName(Protocols.SOURCE)
    @Expose
    private Source source;
    @SerializedName(Protocols.AUTHOR)
    @Expose
    private String author;
    @SerializedName(Protocols.TITLE)
    @Expose
    private String title;
    @SerializedName(Protocols.DESCRIPTION)
    @Expose
    private String description;
    @SerializedName(Protocols.URL)
    @Expose
    private String url;
    @SerializedName(Protocols.URL_IMAGE)
    @Expose
    private String urlToImage;
    @SerializedName(Protocols.DATE_PUBLISHED)
    @Expose
    private String publishedAt;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

}
