package io.github.pshegger.infobook.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by pshegger on 2015. 11. 10..
 */
class ExampleFragment : BaseInfoBookFragment() {
    companion object {
        var idCtr: Int = 0

        fun newInstance(name: String): ExampleFragment {
            val fragment = ExampleFragment()

            fragment.myId = idCtr++
            fragment.baseName = name

            return fragment
        }
    }

    private val colors = arrayOf(
            Color.RED, Color.YELLOW, Color.GREEN,
            Color.BLUE, Color.CYAN
    )

    var baseName: String = ""
    var myId: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = View(container?.context)
        v.setBackgroundColor(colors[myId.mod(colors.size)])

        return v
    }

    override fun name(): String = "$baseName$myId"
}