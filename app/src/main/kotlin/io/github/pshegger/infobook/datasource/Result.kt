package io.github.pshegger.infobook.datasource

/**
 * Created by pshegger on 2015. 11. 11..
 */
data class Result<T>(val result: T?, val age: Long, val source: SourceType, val loadResult: LoadResult)