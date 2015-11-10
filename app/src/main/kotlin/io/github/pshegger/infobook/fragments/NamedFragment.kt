package io.github.pshegger.infobook.fragments

import android.support.v4.app.Fragment

/**
 * Created by pshegger on 2015. 11. 10..
 */
abstract class NamedFragment : Fragment() {
    abstract fun name(): String
}