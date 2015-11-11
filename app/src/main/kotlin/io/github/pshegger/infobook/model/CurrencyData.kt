package io.github.pshegger.infobook.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pshegger on 2015. 11. 11..
 */
data class CurrencyData(
        @SerializedName("id") val id: Int,
        @SerializedName("order") val order: Int,
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("icon") val iconURL: String,
        var amount: Int = 0) : Comparable<CurrencyData> {
    override fun compareTo(other: CurrencyData): Int {
        return order - other.order
    }
}