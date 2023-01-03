package com.safr.binlist.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safr.binlist.core.DataState
import com.safr.binlist.data.local.model.History
import com.safr.binlist.domain.usecases.DetailsApiUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(private val apiBank: DetailsApiUseCases): ViewModel() {

    private val idMut = MutableLiveData<Int>()

    private val mutListHistory: MutableStateFlow<History?> =
        MutableStateFlow(History())
    val detailListDrink = mutListHistory.asStateFlow()

    fun start(id: Int) {
        idMut.value = id
        viewModelScope.launch {
            Log.d("lol", " fun start $id value ${idMut.value}")
            apiBank.getDetails(idMut.value ?: 15346)
                .onEach { state ->
                    when (state) {
                        is DataState.Error -> state.exception.also {
//                            mutIsDataLoading.value = false
//                            mutIsError.value = true
                        }
                        is DataState.Loading ->  Log.d("lol", " Loading ${idMut.value}")
                        is DataState.Success -> {
                            Log.d("lol", " Success ${idMut.value}")
                            Log.d("lol", " Success last ${state.data}")

                            mutListHistory.value = state.data
//                            mutIsDataLoading.value = false
//                            checkStar()
                        }
                    }
                }.launchIn(viewModelScope)
        }

    }

}