package newsapp.gab.com.newsapp.components;

import javax.inject.Singleton;

import dagger.Component;
import newsapp.gab.com.newsapp.MainActivity;
import newsapp.gab.com.newsapp.activities.NewsListActivity;
import newsapp.gab.com.newsapp.modules.AppModule;
import newsapp.gab.com.newsapp.modules.NetModule;

/**
 * Created by Gabriel Angelo Inot on 19/03/2018.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(NewsListActivity activity);
    void inject(MainActivity activity);

}
