package com.alura.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val questionBank = listOf(
        Question(R.string.question_amazonas, true),
        Question(R.string.question_australia, true),
        Question(R.string.question_bahia, false),
        Question(R.string.question_sampa, false),
        Question(R.string.question_rio, true))

    private var currentIndex=0
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton : Button
    private lateinit var questionTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "GeoQuiz"

        trueButton = findViewById<Button>(R.id.bt_verdade)
        falseButton = findViewById<Button>(R.id.bt_falso)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view: View ->
            val toast = Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            )
            toast.setGravity(1, 1 ,0)
            toast.show()
            Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
                )
                .show()
           checkAnswer(true)
        }

        falseButton.setOnClickListener{ view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener{
            if((currentIndex+1)<questionBank.size){
                currentIndex++
            } else{
                currentIndex=0
            }

            updateQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        } else{
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}