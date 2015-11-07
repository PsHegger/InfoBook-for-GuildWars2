package io.github.pshegger.infobook.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import io.github.pshegger.infobook.GWApiService;
import io.github.pshegger.infobook.InfoBookApplication;
import io.github.pshegger.infobook.model.FileData;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by pshegger on 2015.10.15..
 */
public class IconLoadHelper {
    public enum IconType {
        Item, File
    }

    private static Map<IconType, Map<String, String>> urlCache = new HashMap<>();
    static {
        urlCache.put(IconType.File, new HashMap<String, String>());
        urlCache.put(IconType.Item, new HashMap<String, String>());
    }

    public static void loadIcon(IconType type, String id, ImageView target) {
        InfoBookApplication app = InfoBookApplication.getInstance();

        if (urlCache.get(type).containsKey(id)) {
            String url = urlCache.get(type).get(id);
            Picasso.with(app)
                    .load(url)
                    .into(target);

            return;
        }

        switch (type) {
            case Item:
                loadItem(id, target);
                break;
            case File:
                loadFile(app, id, target);
                break;
        }
    }

    private static void loadItem(String id, ImageView target) {

    }

    private static void loadFile(final InfoBookApplication app, final String id, final ImageView target) {
        final GWApiService service = app.getService();

        service.getItem(id).enqueue(new Callback<FileData>() {
            @Override
            public void onResponse(Response<FileData> response, Retrofit retrofit) {
                String url = response.body().getUrl();

                urlCache.get(IconType.File).put(id, url);

                Picasso.with(app)
                        .load(url)
                        .into(target);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
