package com.classnumber_00_domaekazuki.st42_kadai02_sample

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
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
        val name = intent.getStringExtra("name")
        Log.i("ポケモン", name.toString())
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val detail = api.getPokemonDetail(name.toString())
                val textViewName: TextView = findViewById(R.id.textViewName)
                textViewName.text = "名前: ${detail.name}"
                val textViewHeight: TextView = findViewById(R.id.textViewHeight)
                textViewHeight.text = "高さ: ${detail.height}"
                val textViewWeight: TextView = findViewById(R.id.textViewWeight)
                textViewWeight.text = "重さ: ${detail.weight}"
                val bitmap = withContext(Dispatchers.IO) {
                    BitmapFactory.decodeStream(URL(detail.sprites.frontImage).openStream())
                }
                val shinyImageView: ImageView = findViewById(R.id.imageViewShiny)
                shinyImageView.setImageBitmap(bitmap)

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@ResultActivity, "詳細取得失敗", Toast.LENGTH_SHORT).show()
            }
        }
        val backButton: Button = findViewById(R.id.buttonBack)
        backButton.setOnClickListener {
            finish()
        }
    }
}