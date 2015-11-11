package io.github.pshegger.infobook

import android.app.Application
import android.preference.PreferenceManager
import com.google.gson.GsonBuilder
import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Response
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import java.util.*

/**
 * Created by pshegger on 2015. 11. 09..
 */
class InfoBookApplication : Application() {
    companion object {
        val RNG = Random()
        lateinit var instance: InfoBookApplication
    }

    var apiKey: String = ""
        get() = field
        set(value) {
            field = value
            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putString(Constants.Preferences.API_KEY, value)
                    .apply()
            service = getNewApiService(value)
        }

    var service: GWApiService = getNewApiService()

    override fun onCreate() {
        super.onCreate()

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        apiKey = prefs.getString(
                Constants.Preferences.API_KEY,
                "3674D0F3-B7A8-2242-B4C6-FE2E065C86A84C0DB899-DD85-490C-9986-1DA8E629CD21"
        )

        instance = this
    }

    private fun getNewApiService(newApiKey: String = apiKey): GWApiService {
        val client = OkHttpClient().apply {
            interceptors().add(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val builder = chain.request().newBuilder()

                    builder.addHeader("Authorization", "Bearer $newApiKey")

                    return chain.proceed(builder.build())
                }
            })
        }

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()

        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.GW_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit.create(GWApiService::class.java)
    }
}