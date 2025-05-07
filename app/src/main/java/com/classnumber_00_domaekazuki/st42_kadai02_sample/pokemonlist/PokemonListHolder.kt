package com.classnumber_00_domaekazuki.st42_kadai02_sample.pokemonlist

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.classnumber_00_domaekazuki.st42_kadai02_sample.R

// ポケモン一覧に表示する1つの画像レイアウトを保持する
class PokemonListHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val pokemonImage: ImageView = itemView.findViewById(R.id.pokemonListImage)
}