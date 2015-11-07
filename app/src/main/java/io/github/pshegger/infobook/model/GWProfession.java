package io.github.pshegger.infobook.model;

import io.github.pshegger.infobook.R;

/**
 * Created by pshegger on 2015.10.04..
 */
public enum GWProfession {
    Guardian, Warrior, Revenant,
    Necromancer, Mesmer, Elementalist,
    Thief, Ranger, Engineer;

    public int getTheme() {
        switch (this) {
            case Guardian:
                return R.style.AppTheme_Guardian_NoActionBar;
            case Warrior:
                return R.style.AppTheme_Warrior_NoActionBar;
            case Revenant:
                return R.style.AppTheme_Revenant_NoActionBar;
            case Necromancer:
                return R.style.AppTheme_Necromancer_NoActionBar;
            case Mesmer:
                return R.style.AppTheme_Mesmer_NoActionBar;
            case Elementalist:
                return R.style.AppTheme_Elementalist_NoActionBar;
            case Thief:
                return R.style.AppTheme_Thief_NoActionBar;
            case Ranger:
                return R.style.AppTheme_Ranger_NoActionBar;
            case Engineer:
                return R.style.AppTheme_Engineer_NoActionBar;
        }

        return R.style.AppTheme_Elementalist_NoActionBar;
    }

    public int getPrimaryColorResource() {
        switch (this) {
            case Guardian:
                return R.color.colorPrimaryGuardian;
            case Warrior:
                return R.color.colorPrimaryWarrior;
            case Revenant:
                return R.color.colorPrimaryRevenant;
            case Necromancer:
                return R.color.colorPrimaryNecromancer;
            case Mesmer:
                return R.color.colorPrimaryMesmer;
            case Elementalist:
                return R.color.colorPrimaryElementalist;
            case Thief:
                return R.color.colorPrimaryThief;
            case Ranger:
                return R.color.colorPrimaryRanger;
            case Engineer:
                return R.color.colorPrimaryEngineer;
        }

        return R.color.colorPrimaryElementalist;
    }

    public String getIconId(boolean big) {
        StringBuilder id = new StringBuilder("icon_");

        id.append(name().toLowerCase());
        if (big) id.append("_big");

        return id.toString();
    }
}
