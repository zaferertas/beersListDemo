package com.xxxxx.buscadordecervezas.data

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun searchBeers(searchedText: String): Flow<List<Beer>>
}