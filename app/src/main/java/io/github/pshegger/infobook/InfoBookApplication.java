package io.github.pshegger.infobook;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Random;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by pshegger on 2015.10.04..
 */
public class InfoBookApplication extends Application {
    public static Random RNG;

    private static InfoBookApplication instance;
    private GWApiService mApiService;

    private String mAPIKey;

    public static InfoBookApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        RNG = new Random();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        mAPIKey = prefs.getString(
                Constants.Preferences.API_KEY,
                "3674D0F3-B7A8-2242-B4C6-FE2E065C86A84C0DB899-DD85-490C-9986-1DA8E629CD21"
        );

        instance = this;
        initRetrofit();
    }

    private void initRetrofit() {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();

                builder.addHeader("Authorization", "Bearer " + mAPIKey);

                return chain.proceed(builder.build());
            }
        });

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.GW_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = retrofit.create(GWApiService.class);
    }

    public GWApiService getService() {
        return mApiService;
    }

    public String getAPIKey() {
        return mAPIKey;
    }

    public void setAPIKey(String key) {
        mAPIKey = key;
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .putString(
                        Constants.Preferences.API_KEY,
                        key
                ).apply();

        initRetrofit();
    }
}
