package com.valmiraguiar.detail.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azul.domain.business.DetailChampionBusiness
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ChampionDetailViewModel(
    private val detailChampionBusiness: DetailChampionBusiness,
    private val coroutineDispatcher: CoroutineContext
) : ViewModel() {

    private val _detailChampionState: MutableStateFlow<DetailChampionState> =
        MutableStateFlow(DetailChampionState.Loading)
    val detailChampionState: StateFlow<DetailChampionState> get() = _detailChampionState

    fun loadChampionDetail(champion: String) {
        viewModelScope.launch {
            withContext(coroutineDispatcher) {
                detailChampionBusiness.getDetailChampion(champion)
                    .onStart {
                        _detailChampionState.value = DetailChampionState.Loading
                    }.catch {
                        _detailChampionState.value = DetailChampionState.Error("${it.message}")
                    }.collect {
                        _detailChampionState.value = DetailChampionState.Success(it)
                    }
            }
        }
    }
}