package io.github.pshegger.infobook.datasource

import io.github.pshegger.infobook.model.CharacterData
import rx.Observable

/**
 * Created by pshegger on 2015. 11. 11..
 */
interface DataLoader {
    fun getCharactersList(): Observable<Result<MutableList<String>>>
    fun getAllCharacters(): Observable<Result<MutableList<CharacterData>>>
}