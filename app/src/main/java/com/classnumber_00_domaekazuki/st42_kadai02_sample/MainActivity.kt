package com.classnumber_00_domaekazuki.st42_kadai02_sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.classnumber_00_domaekazuki.st42_kadai02_sample.pokemonlist.PokemonListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(PokeAPIService::class.java)

        val PokemonList: RecyclerView = findViewById(R.id.pokemonList)
        PokemonList.layoutManager = GridLayoutManager(this, 3)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = api.getPokemonList()
                val adapter = PokemonListAdapter(result.results) { pokemon ->
                    val intent = Intent(this@MainActivity, ResultActivity::class.java)
                    Log.i("ポケモン",pokemon.name)
                    intent.putExtra("name", pokemon.name)
                    startActivity(intent)
                }
                PokemonList.adapter = adapter
            }catch (e: Exception) {
                e.printStackTrace()
                Log.e("MainActivity", "API呼び出し失敗: ${e.message}")
                Toast.makeText(this@MainActivity, "ポケモンの取得に失敗しました", Toast.LENGTH_SHORT).show()
            }
        }
    }
}