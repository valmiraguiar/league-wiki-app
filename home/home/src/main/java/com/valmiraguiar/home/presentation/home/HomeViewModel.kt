package com.valmiraguiar.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valmiraguiar.core.sharedentity.champion.Champion
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

    private val _searchQueryState: MutableStateFlow<String> = MutableStateFlow("")
    val searchQueryState: StateFlow<String> get() = _searchQueryState

    fun loadChampions() {
        viewModelScope.launch {
            withContext(coroutineDispatcher) {
                _searchQueryState.value = ""

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

    fun onSearchQueryChange(query: String) {
        _searchQueryState.value = query
    }

    fun getFilteredChampionList(
        championList: List<Champion>,
        query: String
    ): List<Champion> = championList.filter {
        it.name.contains(query, ignoreCase = true)
    }
}