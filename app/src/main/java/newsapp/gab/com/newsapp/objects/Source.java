package newsapp.gab.com.newsapp.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import newsapp.gab.com.newsapp.postutilities.Protocols;

/**
 * Created by gabriel on 19/03/2018.
 */

public class Source {

    @SerializedName(Protocols.SOURCE_ID)
    @Expose
    private String id;
    @SerializedName(Protocols.SOURCE_NAME)
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
