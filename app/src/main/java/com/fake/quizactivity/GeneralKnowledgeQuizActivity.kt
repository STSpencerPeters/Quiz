package com.fake.quizactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle

class GeneralKnowledgeQuizActivity : AppCompatActivity(){
    private var currentQuestionIndex = 0
    private val questions = listOf(
        Question("Who is the Greek God of the Sun?", listOf("Apollo", "Zeus", "Hades", "Hermes"), 1),
        Question("What is the capital city of France?", listOf("Madrid", "Berlin", "Rome", "Paris"), 4),
        Question("Which planet is known as the Red Planet?", listOf("Earth", "Mars", "Venus", "Jupiter"), 2),
        Question("Who wrote 'Romeo and Juliet'?", listOf("Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain"), 2),
        Question("What is the chemical symbol for gold?", listOf("Au", "Ag", "Pb", "Fe"), 1),
        Question("How many continents are there on Earth?", listOf("5", "6", "7", "8"), 3),
        Question("Which ocean is the largest by area?", listOf("Atlantic Ocean", "Indian Ocean", "Pacific Ocean", "Arctic Ocean"), 3),
        Question("What is the powerhouse of the cell?", listOf("Nucleus", "Mitochondria", "Ribosome", "Golgi Apparatus"), 2),
        Question("Which country is famous for inventing pizza?", listOf("France", "Italy", "Greece", "Spain"), 2),
        Question("What is the longest river in the world?", listOf("Amazon River", "Nile River", "Yangtze River", "Mississippi River"), 2)

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
                goToResults("GeneralKnowlegde")
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