package com.xxxxx.buscadordecervezas.data


import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override fun searchBeers(searchedText: String): Flow<List<Beer>> = flow {
        try {
            val response = apiService.getBeers(PAGE_NUMBER, PAGE_SIZE, searchedText)
            emit(response)
        } catch (e: Throwable) {
            Log.i("zfrD", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val PAGE_NUMBER = 1
        private const val PAGE_SIZE = 20
    }

}