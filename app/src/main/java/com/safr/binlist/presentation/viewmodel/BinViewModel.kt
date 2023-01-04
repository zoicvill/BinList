package com.safr.binlist.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safr.binlist.core.DataState
import com.safr.binlist.core.toDomain
import com.safr.binlist.core.toEntity
import com.safr.binlist.data.network.model.ResponseDataBank
import com.safr.binlist.domain.model.HistoryItem
import com.safr.binlist.domain.usecases.DataBaseUseCases
import com.safr.binlist.domain.usecases.DetailsApiUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val apiBank: DetailsApiUseCases,
    private val db: DataBaseUseCases
) : ViewModel() {

    private val idMut = MutableLiveData<Int>()

    private val mutListHistory: MutableStateFlow<ResponseDataBank?> =
        MutableStateFlow(ResponseDataBank())
    val detailListDrink = mutListHistory.asStateFlow()

    private val mutIsDataLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isDataLoading: StateFlow<Boolean> = mutIsDataLoading

    private val mutIsError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = mutIsError

    private val _readAllBin = MutableStateFlow<List<HistoryItem>>(listOf())
    var readAllBin = _readAllBin.asStateFlow()

    fun insertData(item: HistoryItem){
        viewModelScope.launch {
            db.insDbHistory(item.toEntity())
        }
    }
    init {
        readData()
    }

    private fun readData() {
      try{
            db.getDbHistory().onEach { resource ->
                _readAllBin.value = resource.map { it.toDomain() }

            }.launchIn(viewModelScope)
        }
        catch (e: Exception){
            Log.d("log", " BinViewModel fun readData() ${e.message}")
        }
    }





    fun start(id: Int) {
        idMut.value = id
        viewModelScope.launch {
            Log.d("lol", " fun start $id value ${idMut.value}")
            apiBank.getDetails(idMut.value ?: 45717360)
                .onEach { state ->
                    when (state) {
                        is DataState.Error -> state.exception.also {
                            mutIsDataLoading.value = false
                            mutIsError.value = true
                        }
                        is DataState.Loading -> mutIsDataLoading.value = true
                        is DataState.Success -> {
                            mutListHistory.value = state.data
                            mutIsDataLoading.value = false
                            if (state.data?.bank?.name != null){
                                insertData(HistoryItem(bin= idMut.value.toString(), nameBank = state.data.bank.name ))
                            }
                            readData()
                        }
                    }
                }.launchIn(viewModelScope)
        }

    }


}