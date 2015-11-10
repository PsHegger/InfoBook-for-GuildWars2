package io.github.pshegger.infobook.fragments.account

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.pshegger.infobook.InfoBookApplication
import io.github.pshegger.infobook.fragments.BaseInfoBookFragment

/**
 * Created by pshegger on 2015. 11. 10..
 */
class OtherFragment : BaseInfoBookFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = View(container?.context)
        v.setBackgroundColor(
                Color.rgb(
                        InfoBookApplication.RNG.nextInt(255),
                        InfoBookApplication.RNG.nextInt(255),
                        InfoBookApplication.RNG.nextInt(255)
                )
        )

        return v
    }

    override fun name(): String = "Other"
}