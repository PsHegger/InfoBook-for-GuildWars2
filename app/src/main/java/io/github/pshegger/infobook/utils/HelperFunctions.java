package io.github.pshegger.infobook.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import io.github.pshegger.infobook.GWApiService;
import io.github.pshegger.infobook.InfoBookApplication;
import io.github.pshegger.infobook.model.FileData;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by pshegger on 2015.10.04..
 */
public class HelperFunctions {
    public static void loadFileToImageView(String fileId, final ImageView iv) {
        loadFileToImageView(
                InfoBookApplication.getInstance(),
                InfoBookApplication.getInstance().getService(),
                fileId,
                iv
        );
    }

    private static void loadFileToImageView(final Context ctx, GWApiService service, String fileId, final ImageView iv) {
        Call<FileData> fileLoad = service.getItem(fileId);
        fileLoad.enqueue(new Callback<FileData>() {
            @Override
            public void onResponse(Response<FileData> response, Retrofit retrofit) {
                Picasso.with(ctx)
                        .load(response.body().getUrl())
                        .into(iv);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
