package newsapp.gab.com.newsapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import newsapp.gab.com.newsapp.R;
import newsapp.gab.com.newsapp.objects.Article;

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.author)
    TextView author;

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.link)
    TextView link;

    @BindView(R.id.loading)
    ProgressBar loading;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        String articleObject = getIntent().getStringExtra("Article");

        Article article = new Gson().fromJson(articleObject, Article.class);

        loadDetails(article);
    }

    void loadDetails(Article article) {

        title.setText(article.getTitle());
        author.setText("Author: " + article.getAuthor());
        date.setText("Date: " + article.getPublishedAt() == null ? "" : article.getPublishedAt());
        description.setText(article.getDescription());

        StringBuilder htmlLink = new StringBuilder();
        htmlLink.append("<a href=\"" + article.getUrl() + "\">Click to see full story</a>");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            link.setText(Html.fromHtml(htmlLink.toString(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            link.setText(Html.fromHtml(htmlLink.toString()));
        }

        link.setMovementMethod(LinkMovementMethod.getInstance());


        Glide.with(getApplicationContext())
                .load(article.getUrlToImage())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        loading.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        loading.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(image);



    }
}
