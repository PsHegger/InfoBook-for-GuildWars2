package io.github.pshegger.infobook.model

import io.github.pshegger.infobook.R

/**
 * Created by pshegger on 2015. 11. 10..
 */
enum class GWProfession {
    Guardian, Warrior, Revenant,
    Necromancer, Mesmer, Elementalist,
    Thief, Ranger, Engineer;

    fun theme(): Int = when (this) {
        Guardian -> R.style.AppTheme_Guardian_NoActionBar
        Warrior -> R.style.AppTheme_Warrior_NoActionBar
        Revenant -> R.style.AppTheme_Revenant_NoActionBar
        Necromancer -> R.style.AppTheme_Necromancer_NoActionBar
        Mesmer -> R.style.AppTheme_Mesmer_NoActionBar
        Elementalist -> R.style.AppTheme_Elementalist_NoActionBar
        Thief -> R.style.AppTheme_Thief_NoActionBar
        Ranger -> R.style.AppTheme_Ranger_NoActionBar
        Engineer -> R.style.AppTheme_Engineer_NoActionBar
    }

    fun primaryColorRes(): Int = when (this) {
        Guardian -> R.color.colorPrimaryGuardian
        Warrior -> R.color.colorPrimaryWarrior
        Revenant -> R.color.colorPrimaryRevenant
        Necromancer -> R.color.colorPrimaryNecromancer
        Mesmer -> R.color.colorPrimaryMesmer
        Elementalist -> R.color.colorPrimaryElementalist
        Thief -> R.color.colorPrimaryThief
        Ranger -> R.color.colorPrimaryRanger
        Engineer -> R.color.colorPrimaryEngineer
    }

    fun iconId(big: Boolean): String = "icon_${name.toLowerCase()}${if (big) "_big" else ""}"
}