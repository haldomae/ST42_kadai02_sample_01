package com.classnumber_00_domaekazuki.st42_kadai02_sample

data class PokemonListResponse(
    val results: List<PokemonEntry>
)
data class PokemonEntry(
    val name: String,
    val url: String
)
