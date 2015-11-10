package io.github.pshegger.infobook.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pshegger on 2015. 11. 10..
 */
class FileData(
        @SerializedName("id") val id: String,
        @SerializedName("icon") val url: String)