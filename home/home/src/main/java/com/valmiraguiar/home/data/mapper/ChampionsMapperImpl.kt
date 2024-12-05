package com.valmiraguiar.home.data.mapper

import com.valmiraguiar.core.sharedentity.champion.Champion
import com.valmiraguiar.core.sharedentity.response.ChampionListResponse

class ChampionsMapperImpl : ChampionsMapper {
    override fun convert(dtoData: ChampionListResponse): List<Champion> {
        return dtoData.data.map {
            Champion(
                id = it.id,
                key = it.key,
                name = it.name,
                title = it.title,
                blurb = it.blurb
            )
        }
    }
}