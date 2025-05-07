package com.classnumber_00_domaekazuki.st42_kadai02_sample

import retrofit2.http.GET
import retrofit2.http.Path

interface PokeAPIService {
    @GET("pokemon?limit=151")
    suspend fun getPokemonList(): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String):  PokemonDetail
}