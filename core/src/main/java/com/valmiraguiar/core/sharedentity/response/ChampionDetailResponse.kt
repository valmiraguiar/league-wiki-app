package com.valmiraguiar.core.sharedentity.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChampionDetailResponse(
    val data: Map<String,ChampionDetailDataResponse>
) : Parcelable