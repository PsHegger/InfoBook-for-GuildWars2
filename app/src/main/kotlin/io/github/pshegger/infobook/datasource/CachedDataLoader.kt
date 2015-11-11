package io.github.pshegger.infobook.datasource

import io.github.pshegger.infobook.model.CharacterData
import rx.Observable

/**
 * Created by pshegger on 2015. 11. 11..
 */
object CachedDataLoader : DataLoader {
    override fun getAllCharacters(): Observable<Result<MutableList<CharacterData>>> {
        return Observable.empty()
    }

    override fun getCharactersList(): Observable<Result<MutableList<String>>> {
        return Observable.empty()
    }
}