package newsapp.gab.com.newsapp;

import android.app.Application;

import newsapp.gab.com.newsapp.components.DaggerNetComponent;
import newsapp.gab.com.newsapp.components.NetComponent;
import newsapp.gab.com.newsapp.modules.AppModule;
import newsapp.gab.com.newsapp.modules.NetModule;
import newsapp.gab.com.newsapp.postutilities.URL;

/**
 * Created by Gabriel Angelo Inot on 19/03/2018.
 */

public class AppInitialization extends Application {

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(URL.host))
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }





}
