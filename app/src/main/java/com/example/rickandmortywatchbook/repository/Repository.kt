package com.example.rickandmortywatchbook.repository

import androidx.lifecycle.MutableLiveData
import com.example.rickandmortywatchbook.data.models.CharactersResponse
import com.example.rickandmortywatchbook.data.service.ApiService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    val BASE_URL = "https://rickandmortyapi.com"
    val apiService: ApiService
    var characters: CharactersResponse? = null

    init {
        val client = OkHttpClient.Builder().build()
        apiService = Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }
    /* fun getAllCharacters(): MutableLiveData<CharacterResponse> {
         apiService.getCharacters().enqueue(object : Callback<CharacterResponse> {
             override fun onResponse(
                 call: Call<CharacterResponse>,
                 response: Response<CharacterResponse>
             ) {
                 characters.value = response.body()
             }
             override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                 t.printStackTrace()
             }
         })
         return characters
     }*/
}


