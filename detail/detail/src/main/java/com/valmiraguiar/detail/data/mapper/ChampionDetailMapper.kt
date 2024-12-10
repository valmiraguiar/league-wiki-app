package com.valmiraguiar.detail.data.mapper

import com.valmiraguiar.core.entities.DataConverter
import com.valmiraguiar.core.sharedentity.champion.ChampionDetail
import com.valmiraguiar.core.sharedentity.response.ChampionDetailResponse

interface ChampionDetailMapper : DataConverter<ChampionDetailResponse, ChampionDetail>