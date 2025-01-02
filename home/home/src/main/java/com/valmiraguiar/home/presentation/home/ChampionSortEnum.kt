package com.valmiraguiar.home.presentation.home

enum class SortEnum {
    ASC,
    DESC
}

sealed interface ChampionSort {
    data class Name(val from: SortEnum) : ChampionSort
}