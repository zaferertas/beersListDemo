package com.xxxxx.buscadordecervezas.ui.beerlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.xxxxx.buscadordecervezas.data.Beer
import com.xxxxx.buscadordecervezas.data.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {

    private val searchChannel = ConflatedBroadcastChannel<String>()

    private var _beers = searchChannel
        .asFlow()
        .debounce(500)
        .flatMapLatest { search ->
            repository.searchBeers(search)
        }
        .catch { throwable ->
            _error.value = throwable.localizedMessage
        }.asLiveData()

    val beers: LiveData<List<Beer>>
        get() = _beers

    val error: LiveData<String>
        get() = _error
    private var _error = MutableLiveData<String>()

    init {
        searchChannel.offer("malt")
    }

    fun search(query: String) {
        searchChannel.offer(query)
    }
}