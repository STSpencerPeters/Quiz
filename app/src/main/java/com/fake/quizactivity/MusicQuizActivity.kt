package com.fake.quizactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MusicQuizActivity : AppCompatActivity(){
    private var currentQuestionIndex = 0
    private val questions = listOf(
        Question("Who is known as the King of Pop?", listOf("Elvis Presley", "Michael Jackson", "Prince", "Frank Sinatra"), 2),
        Question("Which instrument has 88 keys?", listOf("Violin", "Guitar", "Piano", "Saxophone"), 3),
        Question("Which band released the song 'Bohemian Rhapsody'?", listOf("The Beatles", "Queen", "The Rolling Stones", "Led Zeppelin"), 2),
        Question("What is the real name of the artist known as Eminem?", listOf("Marshall Mathers", "Curtis Jackson", "Shawn Carter", "Christopher Wallace"), 1),
        Question("Which female artist released the album '1989'?", listOf("Beyoncé", "Adele", "Taylor Swift", "Katy Perry"), 3),
        Question("Which musical symbol represents a note held for four beats?", listOf("Quarter note", "Half note", "Whole note", "Eighth note"), 3),
        Question("Who was the lead singer of Nirvana?", listOf("Kurt Cobain", "Eddie Vedder", "Chris Cornell", "Dave Grohl"), 1),
        Question("Which composer wrote 'Für Elise'?", listOf("Mozart", "Beethoven", "Bach", "Chopin"), 2),
        Question("Which genre of music is often associated with turntables and scratching?", listOf("Jazz", "Hip-Hop", "Classical", "Reggae"), 2),
        Question("Who sang 'Someone Like You'?", listOf("Adele", "Rihanna", "Mariah Carey", "Celine Dion"), 1)

    )

    private var correctAnswers = 0
    private val startTime = System.currentTimeMillis()

    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        showQuestion()

        findViewById<Button>(R.id.nextButton).setOnClickListener({
            checkAnswers()
            currentQuestionIndex++
            if(currentQuestionIndex < questions.size){
                showQuestion()
            }else{
                goToResults("Music")
            }
        })
    }

    private fun showQuestion(){
        val question = questions[currentQuestionIndex]
        findViewById<TextView>(R.id.questionText).text = question.text
        findViewById<RadioButton>(R.id.radioButton1).text = question.options[0]
        findViewById<RadioButton>(R.id.radioButton2).text = question.options[1]
        findViewById<RadioButton>(R.id.radioButton3).text = question.options[2]
        findViewById<RadioButton>(R.id.radioButton4).text = question.options[3]
        findViewById<SeekBar>(R.id.seekBar).progress = (currentQuestionIndex + 1) * 10
    }

    private fun checkAnswers() {
        val selectedAnswer = when {
            findViewById<RadioButton>(R.id.radioButton1).isChecked -> 0
            findViewById<RadioButton>(R.id.radioButton2).isChecked -> 1
            findViewById<RadioButton>(R.id.radioButton3).isChecked -> 2
            findViewById<RadioButton>(R.id.radioButton4).isChecked -> 3
            else -> -1
        }
        if (selectedAnswer == questions[currentQuestionIndex].correctAnswer) {
            correctAnswers++
        }
    }

    private fun goToResults(category : String){
        val elapsedTime = (System.currentTimeMillis() - startTime) / 1000
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("CATEGORY", category)
        intent.putExtra("CORRECT_ANSWERS", correctAnswers)
        intent.putExtra("TIME_TAKEN", elapsedTime)
        startActivity(intent)
        finish()
    }
}