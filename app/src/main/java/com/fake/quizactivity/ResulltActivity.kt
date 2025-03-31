package com.fake.quizactivity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class ResulltActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resullt)

        val resultTextView: TextView = findViewById((R.id.resultText))

        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        val timeTaken = intent.getLongExtra("TIME_TAKEN", 0)

        resultTextView.text = "You got $correctAnswers correct in $timeTaken seconds!"

        saveScoreToFirestore(correctAnswers, timeTaken)
    }

    private fun saveScoreToFirestore(correctAnswers: Int, timeTaken: Long)
    {
        val db = Firebase.firestore
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val category = intent.getStringExtra("CATEGORY") ?: return

        val scoreData = hashMapOf(
            "correctAnswers" to correctAnswers
            "timeTaken" to timeTaken
        )

        db.collection("users").document(userId)
            .collection("scores").document(category)
            .set(scoreData)
            .addOnSuccessListener {
                Log.d("Firestore", "Score saved successfully!")
            }
            .addOnFailureListener{
                Log.e("Firestore", "Error saving score", it)
            }
    }
}