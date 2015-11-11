package io.github.pshegger.infobook.utils

import io.github.pshegger.infobook.datasource.LoadResult
import io.github.pshegger.infobook.datasource.Result
import io.github.pshegger.infobook.datasource.SourceType
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit
import rx.Observer

/**
 * Created by pshegger on 2015. 11. 11..
 */
class DefaultCallback<T>(val observer: Observer<Result<T>>) : Callback<T> {
    override fun onResponse(response: Response<T>?, retrofit: Retrofit?) {
        if (response == null) {
            observer.onNext(networkError())
        } else {
            if (response.body() == null) {
                observer.onNext(networkError())
            } else {
                observer.onNext(Result(
                        response.body(),
                        0,
                        SourceType.Server,
                        LoadResult.LoadedFromServer
                ))
            }
        }
    }

    override fun onFailure(t: Throwable?) {
        observer.onNext(networkError())
    }

    private fun networkError() = Result<T>(null, 0, SourceType.Server, LoadResult.NetworkError)
}