package com.fake.quizactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VideoGamesQuizActivity : AppCompatActivity(){
    private var currentQuestionIndex = 0
    private val questions = listOf(
        Question("Which video game character is known as 'The Blue Blur'?", listOf("Mario", "Link", "Sonic", "Mega Man"), 3),
        Question("What is the best-selling video game of all time?", listOf("Minecraft", "GTA V", "Tetris", "Fortnite"), 1),
        Question("Which company developed the PlayStation console?", listOf("Microsoft", "Sony", "Nintendo", "Sega"), 2),
        Question("In which game do players build and break blocks in a 3D world?", listOf("Roblox", "Terraria", "Minecraft", "Fortnite"), 3),
        Question("What is the name of the princess that Mario is always trying to rescue?", listOf("Princess Peach", "Princess Zelda", "Princess Daisy", "Princess Rosalina"), 1),
        Question("Which first-person shooter game popularized the battle royale genre?", listOf("Call of Duty", "PUBG", "Halo", "Counter-Strike"), 2),
        Question("What is the main objective in Pac-Man?", listOf("Collect coins", "Eat all the pellets", "Defeat enemies", "Reach the finish line"), 2),
        Question("Which open-world game features a vast map called 'Hyrule'?", listOf("Skyrim", "The Witcher 3", "The Legend of Zelda: Breath of the Wild", "Dark Souls"), 3),
        Question("What is the name of the main character in The Witcher series?", listOf("Geralt of Rivia", "Arthur Morgan", "Kratos", "Dante"), 1),
        Question("Which game studio created the Grand Theft Auto series?", listOf("Ubisoft", "Rockstar Games", "EA", "Bethesda"), 2)

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
                goToResults("VideoGames")
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