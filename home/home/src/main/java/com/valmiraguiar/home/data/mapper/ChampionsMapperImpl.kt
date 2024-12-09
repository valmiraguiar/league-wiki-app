package com.valmiraguiar.home.data.mapper

import com.valmiraguiar.core.sharedentity.champion.Champion
import com.valmiraguiar.core.sharedentity.response.ChampionListResponse

class ChampionsMapperImpl : ChampionsMapper {
    override fun convert(dtoData: ChampionListResponse): List<Champion> = dtoData.data.map {
        Champion(
            id = it.value.id,
            key = it.value.key,
            name = it.value.name,
            title = it.value.title,
            blurb = it.value.blurb
        )
    }
}