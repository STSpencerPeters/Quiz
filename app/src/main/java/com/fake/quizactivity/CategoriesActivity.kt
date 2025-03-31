package com.fake.quizactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val categories = listOf("Math", "General Knowledge", "Music", "Video Games")
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CategoriesAdapter(categories) { category ->
            val intent = when (category) {
                "Math" -> Intent(this, MathQuizActivity::class.java)
                "General Knowledge" -> Intent(this, GeneralKnowledgeQuizActivity::class.java)
                "Music" -> Intent(this, MusicQuizActivity::class.java)
                "Video Games" -> Intent(this, VideoGamesQuizActivity::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }
    }
}
