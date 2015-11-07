package io.github.pshegger.infobook.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pshegger on 2015.10.04..
 */
public class FileData {
    @SerializedName("id")
    String id;
    @SerializedName("icon")
    String url;

    public String getUrl() {
        return url;
    }
}
