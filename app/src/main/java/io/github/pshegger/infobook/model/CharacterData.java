package io.github.pshegger.infobook.model;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by pshegger on 2015.10.13..
 */
public class CharacterData implements Comparable<CharacterData> {
    @SerializedName("name")
    String name;
    @SerializedName("race")
    String race;
    @SerializedName("gender")
    String gender;
    @SerializedName("profession")
    String profession;

    @SerializedName("level")
    int level;
    @SerializedName("guild")
    String guild;
    @SerializedName("created")
    Date created;

    @SerializedName("age")
    long age;
    @SerializedName("deaths")
    int deaths;

    public String getName() {
        return name;
    }

    public GWRace getRace() {
        return GWRace.valueOf(race);
    }

    public String getGender() {
        return gender;
    }

    public GWProfession getProfession() {
        return GWProfession.valueOf(profession);
    }

    public int getLevel() {
        return level;
    }

    public String getGuild() {
        return guild;
    }

    public long getAge() {
        return age;
    }

    public int getDeaths() {
        return deaths;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("race", getRace())
                .add("gender", gender)
                .add("profession", getProfession())
                .add("level", level)
                .toString();
    }

    @Override
    public int compareTo(CharacterData another) {
        return getName().compareTo(another.getName());
    }
}
