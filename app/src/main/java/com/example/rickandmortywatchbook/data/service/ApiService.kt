package com.example.rickandmortywatchbook.data.service

import com.example.rickandmortywatchbook.data.models.Character
import com.example.rickandmortywatchbook.data.models.CharactersResponse
import com.example.rickandmortywatchbook.data.models.Episode
import com.example.rickandmortywatchbook.data.models.EpisodeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/api/character")
    fun getCharacters(): Call<CharactersResponse>

    @GET("/api/character/{characterId}")
    fun getCharacterById(@Path("characterId") characterId: String): Call<Character>

    @GET("/api/episode/{episodes}")
    fun getEpisodesPerCharacter(@Path("episodes") episodes: String): Call<List<Episode>>

    @GET("/api/episode/{episodeId}")
    fun getEpisodeById(@Path("episodeId") episodeId: String): Call<Episode>

    @GET("/api/character/{characters}")
    fun getCharactersPerEpisode(@Path("characters") characters: String): Call<List<Character>>


}