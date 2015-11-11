package io.github.pshegger.infobook.datasource

import io.github.pshegger.infobook.model.CharacterData
import rx.Observable

/**
 * Created by pshegger on 2015. 11. 11..
 */
object CachedGWDataSource {
    fun getCharactersList(forceRefresh: Boolean = true): Observable<Result<MutableList<String>>> {
        val fromCache = false || forceRefresh

        return if (fromCache) CachedDataLoader.getCharactersList() else OnlineDataLoader.getCharactersList()
    }

    fun getAllCharacters(forceRefresh: Boolean = true): Observable<Result<MutableList<CharacterData>>> {
        val fromCache = false || forceRefresh

        return if (fromCache) CachedDataLoader.getAllCharacters() else OnlineDataLoader.getAllCharacters()
    }
}