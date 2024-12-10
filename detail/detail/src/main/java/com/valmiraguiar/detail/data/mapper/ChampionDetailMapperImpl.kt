package com.valmiraguiar.detail.data.mapper

import com.valmiraguiar.core.sharedentity.champion.ChampionDetail
import com.valmiraguiar.core.sharedentity.response.ChampionDetailResponse

class ChampionDetailMapperImpl : ChampionDetailMapper {
    override fun convert(dtoData: ChampionDetailResponse): ChampionDetail {
        val first = dtoData.data.values.first()
        return ChampionDetail(
            id = first.id,
            name = first.name,
            key = first.key,
            title = first.title,
            blurb = first.blurb,
            lore = first.lore
        )
    }
}