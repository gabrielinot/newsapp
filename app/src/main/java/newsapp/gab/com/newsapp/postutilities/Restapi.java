package newsapp.gab.com.newsapp.postutilities;

import java.util.List;

import newsapp.gab.com.newsapp.objects.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Gabriel Angelo Inot on 19/03/2018.
 */

public interface Restapi {

    @GET("top-headlines?country=us&category=business&apiKey=" + URL.apiKey)
    Observable<Post> getPosts();

}
