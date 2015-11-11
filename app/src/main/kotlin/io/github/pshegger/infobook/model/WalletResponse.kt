package io.github.pshegger.infobook.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pshegger on 2015. 11. 11..
 */
data class WalletResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("value") val amount: Int)