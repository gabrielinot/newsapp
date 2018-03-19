package newsapp.gab.com.newsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import newsapp.gab.com.newsapp.R;
import newsapp.gab.com.newsapp.interfaces.Notifier;
import newsapp.gab.com.newsapp.objects.Article;

/**
 * Created by Gabriel Angelo Inot on 19/03/2018.
 */

public class NewsListAdapter extends  RecyclerView.Adapter<NewsListAdapter.MyViewHolder> implements View.OnClickListener {

    private List<Article> articles;
    private Context context;
    private Notifier notifier;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView description;
        private ProgressBar loading;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            loading = (ProgressBar) view.findViewById(R.id.loading);
        }
    }

    public NewsListAdapter(List<Article> articles, Context context, Notifier notifier) {
        this.articles = articles;
        this.context = context;
        this.notifier = notifier;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Article article = articles.get(position);

        Glide.with(context)
                .load(article.getUrlToImage())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.loading.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.loading.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.image);


        holder.title.setText(article.getTitle());
        holder.title.setTag(article);
        holder.title.setOnClickListener(this);

        holder.description.setText(article.getDescription());
        holder.description.setTag(article);
        holder.description.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        notifier.notifyActivity(view.getTag());
    }
}
