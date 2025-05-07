package com.classnumber_00_domaekazuki.st42_kadai02_sample

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)

data class Sprites(
    @SerializedName("front_default")
    val frontImage: String,
    @SerializedName("front_shiny")
    val shinyImage: String
)
