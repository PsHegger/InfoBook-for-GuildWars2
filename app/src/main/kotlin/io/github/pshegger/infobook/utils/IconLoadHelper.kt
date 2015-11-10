package io.github.pshegger.infobook.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso
import io.github.pshegger.infobook.InfoBookApplication
import io.github.pshegger.infobook.model.FileData
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit

/**
 * Created by pshegger on 2015. 11. 09..
 */
object IconLoadHelper {
    enum class IconType {
        Item, File
    }

    private val urlCache: Map<IconType, MutableMap<String, String>> = mapOf(
            IconType.File to hashMapOf(),
            IconType.Item to hashMapOf()
    )

    fun loadIcon(type: IconType, id: String, target: ImageView) {
        val app = InfoBookApplication.Companion.instance ?: return;

        urlCache[type]?.let { cache ->
            if (id in cache.keys) {
                val url = cache[id]
                Picasso.with(app)
                        .load(url)
                        .into(target)

                return
            }
        }

        when (type) {
            IconType.Item -> loadItem(app, target)
            IconType.File -> loadFile(app, id, target)
        }
    }

    private fun loadItem(app: InfoBookApplication, target: ImageView) {
    }

    private fun loadFile(app: InfoBookApplication, id: String, target: ImageView) {
        val service = app.service;

        service.getItem(id).enqueue(object : Callback<FileData> {
            override fun onFailure(t: Throwable?) {

            }

            override fun onResponse(response: Response<FileData>?, retrofit: Retrofit) {
                response?.body()?.let { body ->
                    urlCache[IconType.File]?.put(id, body.url)

                    Picasso.with(app)
                            .load(body.url)
                            .into(target)
                }
            }
        })
    }
}