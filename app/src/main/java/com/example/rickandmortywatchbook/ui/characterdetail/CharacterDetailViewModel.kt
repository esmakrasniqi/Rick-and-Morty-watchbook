package com.example.rickandmortywatchbook.ui.characterdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortywatchbook.data.models.Character
import com.example.rickandmortywatchbook.data.models.Episode
import com.example.rickandmortywatchbook.data.models.EpisodeResponse
import com.example.rickandmortywatchbook.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailViewModel : ViewModel() {
    val repository = Repository()
    val characterRespose = MutableLiveData<Character>()
    val episodeResponse = MutableLiveData<List<Episode>>()

    fun getCharacterById(characterId: String) {
        repository.apiService.getCharacterById(characterId).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                val character = response.body()
                character?.let {
                    var episodes = ""
                    it.episode.forEach { episode ->
                        episodes += episode.substring(
                            episode.lastIndexOf("/") + 1,
                            episode.length
                        ) + ","
                    }
                    getEpisodesPerCharacter(episodes)
                    println("episodes" + episodes)
                    characterRespose.postValue(it)
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getEpisodesPerCharacter(episodes: String) {
        repository.apiService.getEpisodesPerCharacter(episodes).enqueue(object : Callback<List<Episode>> {
            override fun onResponse(call: Call<List<Episode>>, response: Response<List<Episode>>) {
                episodeResponse.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Episode>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}