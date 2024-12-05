package com.valmiraguiar.home.data.mapper

import com.valmiraguiar.core.entities.DataConverter
import com.valmiraguiar.core.sharedentity.champion.Champion
import com.valmiraguiar.core.sharedentity.response.ChampionListResponse

interface ChampionsMapper : DataConverter<ChampionListResponse, List<Champion>>