package com.valmiraguiar.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valmiraguiar.domain.business.ChampionBusiness
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class HomeViewModel(
    private val championsBusiness: ChampionBusiness,
    private val coroutineDispatcher: CoroutineContext
) : ViewModel() {

    private val _championListState: MutableStateFlow<ChampionListState> =
        MutableStateFlow(ChampionListState.Loading)
    val championListState: StateFlow<ChampionListState> get() = _championListState

    init {
        loadChampions()
    }

    private fun loadChampions() {
        _championListState.value = ChampionListState.Success(listOf())
        viewModelScope.launch {
            withContext(coroutineDispatcher) {
                championsBusiness.getChampionList()
                    .onStart {
                        _championListState.value = ChampionListState.Loading
                    }.catch {
                        _championListState.value =
                            ChampionListState.Error("${it.message}")
                    }.collect {
                        _championListState.value = ChampionListState.Success(it)
                    }
            }
        }
    }

    fun sortChampions(sortFrom: ChampionSort) {
        if (_championListState.value !is ChampionListState.Success) {
            return
        }

        when (sortFrom) {
            is ChampionSort.Name -> {
                when (sortFrom.from) {
                    SortEnum.ASC -> {
                        val value = _championListState.value as ChampionListState.Success

                        _championListState.value =
                            ChampionListState.Success(value.championList.sortedBy {
                                it.name
                            })
                    }

                    SortEnum.DESC -> {
                        val value = _championListState.value as ChampionListState.Success

                        _championListState.value =
                            ChampionListState.Success(value.championList.sortedByDescending {
                                it.name
                            })
                    }
                }
            }
        }
    }
}