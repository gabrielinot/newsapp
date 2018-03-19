package newsapp.gab.com.newsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import newsapp.gab.com.newsapp.AppInitialization;
import newsapp.gab.com.newsapp.MainActivity;
import newsapp.gab.com.newsapp.R;
import newsapp.gab.com.newsapp.adapters.NewsListAdapter;
import newsapp.gab.com.newsapp.interfaces.Notifier;
import newsapp.gab.com.newsapp.objects.Article;
import newsapp.gab.com.newsapp.objects.Post;
import newsapp.gab.com.newsapp.postutilities.Restapi;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsListActivity extends AppCompatActivity implements View.OnClickListener, Notifier {

    @Inject
    Retrofit retrofit;

    @BindView(R.id.refreshBtn)
    ImageButton refreshBtn;

    @BindView(R.id.newsRecyclerView)
    RecyclerView newsRecyclerView;

    @BindView(R.id.loadingProgressBar)
    ProgressBar loadingProgressBar;

    private NewsListAdapter newsListAdapter;
    private List<Article> articleArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        ButterKnife.bind(this);


        String postObject = getIntent().getStringExtra("Post");

        Post post = new Gson().fromJson(postObject, Post.class);

        if (post != null) {
            articleArrayList = post.getArticles();
        }

        ((AppInitialization) getApplication()).getNetComponent().inject(this);

        initializeComponents();
        initializeNewsListAdapter();

        showProgressBar(false);

    }


    void initializeComponents() {
        refreshBtn.setOnClickListener(this);
    }

    void initializeNewsListAdapter() {

        if (articleArrayList == null)
            articleArrayList = new ArrayList<>();

        newsListAdapter = new NewsListAdapter(articleArrayList, getApplicationContext(), this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        newsRecyclerView.setLayoutManager(mLayoutManager);
        newsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        newsRecyclerView.setAdapter(newsListAdapter);

    }

    void fetchNews() {

        showProgressBar(true);

        Observable<Post> newsService = retrofit.create(Restapi.class).getPosts();

        newsService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onCompleted() {
                        showProgressBar(false);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Post post) {
                        showProgressBar(false);
                        articleArrayList = post.getArticles();
                        newsListAdapter.setArticles(articleArrayList);
                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.refreshBtn:
                fetchNews();
                break;
            default:
                break;
        }
    }

    private void showProgressBar(boolean show) {
        if (show) {
            loadingProgressBar.setVisibility(View.VISIBLE);
            newsRecyclerView.setVisibility(View.GONE);
        } else {
            loadingProgressBar.setVisibility(View.GONE);
            newsRecyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void notifyActivity() {

    }

    @Override
    public void notifyActivity(Object object) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra("Article", new Gson().toJson(object));
        startActivity(intent);
    }
}
