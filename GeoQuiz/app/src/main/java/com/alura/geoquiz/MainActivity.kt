package com.alura.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    //by lazy: somente quando necessário vai acessar este método
    // e fazer a atribuição
    private val quizViewModel: QuizViewModel by lazy {
        //Se tentar chamar a linha abaixo antes do onCreate, vai dae IlegalStateException
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton : ImageButton
    private lateinit var previewButton : ImageButton
    private lateinit var questionTextView : TextView
    private var correctQuestions=0.000
    private val TAG = "MainActivity"
    private var questionsAnswered= mutableListOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        title = "GeoQuiz"
        /*
        //é ums intância da QuizviewModel, na qual retorna um provider, retorna
        val provider: ViewModelProvider = ViewModelProviders.of(this)
        //uma QuizViewModel associada a atividade atual
        val quizViewModel = provider.get(QuizViewModel::class.java)
        Log.d(TAG,"Got a QuizViewModel: $quizViewModel")
        */


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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onRestart(){
        super.onRestart()
        correctQuestions=0.000
        questionsAnswered= mutableListOf<Question>()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")}

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called") }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called") }


    private fun previewQuestion(){
        if((currentIndex-1)<0){
            currentIndex= quizViewModel.getQuestionBankSize() -1
        } else{
            currentIndex--
        }

    }
    private fun nextQuestion(){
        if((currentIndex+1)<quizViewModel.getQuestionBankSize()){
            currentIndex++
            quizViewModel.moveToNext()
        } else{
            correctQuestions=(correctQuestions/quizViewModel.getQuestionBankSize())*100
            Toast.makeText(this,correctQuestions.toString(),Toast.LENGTH_LONG).show()
            correctQuestions=0.000
            currentIndex=0
        }
    }
    private fun updateQuestion() {
        falseButton.setEnabled(true)
        trueButton.setEnabled(true)
        var questionTextResId = quizViewModel.getQuestionByIndice(currentIndex).textResId
        questionTextView.setText(questionTextResId)
        if (quizViewModel.getQuestionBankSize() ==questionsAnswered.size){
            Toast.makeText(this,"questionary restart", Toast.LENGTH_LONG).show()
            correctQuestions=0.000
            questionsAnswered= mutableListOf<Question>()
            currentIndex=0
            updateQuestion()
        }
            for(questionAnswered in questionsAnswered){
                Log.d("a",questionAnswered.textResId.toString())
                if (questionAnswered.textResId.toString().equals(questionTextResId.toString())){
                    falseButton.setEnabled(false)
                    trueButton.setEnabled(false)
                }
            }
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        } else{
            R.string.incorrect_toast
        }
        if (userAnswer == correctAnswer){
            correctQuestions++
        }
        questionsAnswered.add(quizViewModel.getCurrentQuestion())
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        nextQuestion()
        updateQuestion()
    }
}