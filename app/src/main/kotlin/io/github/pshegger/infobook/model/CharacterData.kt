package io.github.pshegger.infobook.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by pshegger on 2015. 11. 10..
 */
data class CharacterData(
        @SerializedName("name") val name: String,
        @SerializedName("race") private val raceString: String,
        @SerializedName("gender") val gender: String,
        @SerializedName("profession") private val professionString: String,
        @SerializedName("level") val level: Int,
        @SerializedName("guild") val representingGuildId: String,
        @SerializedName("created") val createDate: Date,
        @SerializedName("age") val age: Long,
        @SerializedName("deaths") val deaths: Int) : Comparable<CharacterData> {

    fun race(): GWRace = GWRace.valueOf(raceString)
    fun profession(): GWProfession = GWProfession.valueOf(professionString)

    override fun compareTo(other: CharacterData): Int = name.compareTo(other.name)
}