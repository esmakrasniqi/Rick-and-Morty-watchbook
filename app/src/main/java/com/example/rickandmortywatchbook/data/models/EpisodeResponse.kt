package com.example.rickandmortywatchbook.data.models

import com.google.gson.annotations.SerializedName

class EpisodeResponse (
    @SerializedName("info") val info: Info,
    @SerializedName("results") val results: List<Episode>
)