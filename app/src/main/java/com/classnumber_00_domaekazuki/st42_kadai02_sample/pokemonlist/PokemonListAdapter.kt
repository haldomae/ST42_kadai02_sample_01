package com.classnumber_00_domaekazuki.st42_kadai02_sample.pokemonlist

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.classnumber_00_domaekazuki.st42_kadai02_sample.PokemonEntry
import com.classnumber_00_domaekazuki.st42_kadai02_sample.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class PokemonListAdapter(private val itemList: List<PokemonEntry>, private val onClick: (PokemonEntry) -> Unit)
    : RecyclerView.Adapter<PokemonListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_list, parent, false)
        return PokemonListHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: PokemonListHolder, position: Int) {
        val id = position + 1
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val input = URL(imageUrl).openStream()
                val bitmap = BitmapFactory.decodeStream(input)
                withContext(Dispatchers.Main){
                    holder.pokemonImage.setImageBitmap(bitmap)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        holder.itemView.setOnClickListener{
            onClick(itemList[position])
        }
    }
}