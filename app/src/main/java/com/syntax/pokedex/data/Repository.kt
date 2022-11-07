package com.syntax.pokedex.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.syntax.pokedex.data.model.pokemon.Artwork
import com.syntax.pokedex.data.model.pokemon.Other
import com.syntax.pokedex.data.model.pokemon.Pokemon
import com.syntax.pokedex.data.model.pokemon.PokemonList
import com.syntax.pokedex.data.remote.PokeApi

class Repository(private val api: PokeApi) {

    private val _pokemons = MutableLiveData<List<PokemonList>>()
    val pokemons: LiveData<List<PokemonList>>
        get() = _pokemons

    private val _artWork = MutableLiveData<Artwork>()
    val artWork: LiveData<Artwork>
        get() = _artWork

    private val _pokemonDetail = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemonDetail

    suspend fun loadPokemons() {
        try {

            val response = api.retrofitservice.getPokemonList()
            _pokemons.value = response.results

        }catch (e:Exception){
            Log.e("Repository", e.message.toString())
        }
    }

    suspend fun loadPokemonArtwork(name: String){
        try {
            val response = api.retrofitservice.getPokemon(name)
            _artWork.value = response.sprites.other.officialArtwork

        }catch (e: Exception){
            Log.e("Repository", e.message.toString())
        }
    }

    suspend fun loadPokemonDetails(name: String){
        try {
            val response = api.retrofitservice.getPokemon(name)
            _pokemonDetail.value = response

        }catch (e:Exception){
            Log.e("Repository", e.message.toString())
        }
    }
}