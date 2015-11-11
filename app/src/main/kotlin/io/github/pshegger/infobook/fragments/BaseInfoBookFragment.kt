package io.github.pshegger.infobook.fragments

import io.github.pshegger.infobook.GWApiService
import io.github.pshegger.infobook.InfoBookApplication

/**
 * Created by pshegger on 2015. 11. 10..
 */
abstract class BaseInfoBookFragment : NamedFragment() {
    fun apiService(): GWApiService = InfoBookApplication.instance.service
}