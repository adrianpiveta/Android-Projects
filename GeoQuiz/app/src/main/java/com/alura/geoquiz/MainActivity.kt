package com.alura.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
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
    private lateinit var nextButton : ImageButton
    private lateinit var previewButton : ImageButton
    private lateinit var questionTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "GeoQuiz"

        trueButton = findViewById<Button>(R.id.bt_verdade)
        falseButton = findViewById<Button>(R.id.bt_falso)
        previewButton = findViewById<ImageButton>(R.id.preview_button)
        nextButton = findViewById<ImageButton>(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view: View ->
           checkAnswer(true)
        }

        falseButton.setOnClickListener{ view: View ->
            checkAnswer(false)
        }

        previewButton.setOnClickListener{view: View ->
            previewQuestion()
            updateQuestion()
        }

        nextButton.setOnClickListener{
            nextQuestion()
            updateQuestion()
        }
        updateQuestion()
    }


    private fun previewQuestion(){
        if((currentIndex-1)<0){
            currentIndex=questionBank.size-1
        } else{
            currentIndex--
        }

    }
    private fun nextQuestion(){
        if((currentIndex+1)<questionBank.size){
            currentIndex++
        } else{
            currentIndex=0
        }
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
        nextQuestion()
        updateQuestion()
    }
}