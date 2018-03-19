package newsapp.gab.com.newsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import newsapp.gab.com.newsapp.activities.NewsDetailActivity;
import newsapp.gab.com.newsapp.activities.NewsListActivity;
import newsapp.gab.com.newsapp.objects.Post;
import newsapp.gab.com.newsapp.postutilities.Restapi;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((AppInitialization) getApplication()).getNetComponent().inject(this);

        fetchNews();

    }

    void fetchNews() {

        Observable<Post> newsService = retrofit.create(Restapi.class).getPosts();

        newsService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Post post) {
                        Intent intent = new Intent(MainActivity.this, NewsListActivity.class);
                        intent.putExtra("Post", new Gson().toJson(post));
                        startActivity(intent);
                        MainActivity.this.finish();
                    }
                });

    }


}
