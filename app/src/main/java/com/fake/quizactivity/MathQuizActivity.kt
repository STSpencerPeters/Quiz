package com.fake.quizactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MathQuizActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0
    private val questions = listOf(
        Question("What is 25 X 20", listOf("350", "400", "500", "625"), 3),
        Question("What is 6 * 7?", listOf("36", "42", "48", "54"), 2),
        Question("What is 18 / 3?", listOf("4", "5", "6", "7"), 3),
        Question("What is 11 * 3?", listOf("30", "32", "33", "34"), 3),
        Question("What is 48 / 6?", listOf("7", "8", "9", "10"), 8),
        Question("What is 14 + 28?", listOf("40", "41", "42", "43"), 3),
        Question("What is 100 - 57?", listOf("41", "42", "43", "44"), 3),
        Question("What is 72 / 8?", listOf("7", "8", "9", "10"), 3),
        Question("What is the square of 6?", listOf("30", "32", "36", "38"), 3),
        Question("What is 144 / 12?", listOf("10", "11", "12", "13"), 3)
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
                goToResults("Math")
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