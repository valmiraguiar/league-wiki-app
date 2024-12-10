package com.valmiraguiar.core.sharedentity.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChampionDetailDataResponse(
    @SerializedName("id") val id: String,
    @SerializedName("key") val key: String,
    @SerializedName("name") val name: String,
    @SerializedName("title") val title: String,
    @SerializedName("blurb") val blurb: String,
    @SerializedName("lore") val lore: String,
) : Parcelable