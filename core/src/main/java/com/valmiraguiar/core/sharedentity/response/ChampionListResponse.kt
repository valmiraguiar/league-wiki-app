package com.valmiraguiar.core.sharedentity.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChampionListResponse(
    val data: Map<String, ChampionResponse>
) : Parcelable