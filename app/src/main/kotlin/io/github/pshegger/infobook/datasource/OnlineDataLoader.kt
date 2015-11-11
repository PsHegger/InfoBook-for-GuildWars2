package io.github.pshegger.infobook.datasource

import io.github.pshegger.infobook.InfoBookApplication
import io.github.pshegger.infobook.model.CharacterData
import io.github.pshegger.infobook.utils.DefaultCallback
import rx.Observable
import rx.subjects.BehaviorSubject

/**
 * Created by pshegger on 2015. 11. 11..
 */
object OnlineDataLoader : DataLoader {
    override fun getAllCharacters(): Observable<Result<MutableList<CharacterData>>> {
        val obs = BehaviorSubject.create<Result<MutableList<CharacterData>>>()

        service().getAllCharacters()
                .enqueue(DefaultCallback(obs))

        return obs
    }

    override fun getCharactersList(): Observable<Result<MutableList<String>>> {
        val obs = BehaviorSubject.create<Result<MutableList<String>>>()

        service().getCharactersList()
                .enqueue(DefaultCallback(obs))

        return obs
    }

    private fun service() = InfoBookApplication.instance.service
}