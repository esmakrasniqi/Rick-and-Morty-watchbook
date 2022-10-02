package com.example.rickandmortywatchbook.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortywatchbook.repository.Repository
import com.example.rickandmortywatchbook.data.models.Character
import com.example.rickandmortywatchbook.data.models.CharactersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel : ViewModel() {
    val charactersList: MutableLiveData<List<Character>> = MutableLiveData<List<Character>>()
    val repository = Repository()

    fun getCharacters(){
        repository.apiService.getCharacters().enqueue(object : Callback<CharactersResponse> {
            override fun onResponse(call: Call<CharactersResponse>, response: Response<CharactersResponse>) {
                charactersList.value = response.body()?.results
            }

            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}