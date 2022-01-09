package com.alura.geoquiz

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
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
    private lateinit var cheatButton: Button
    private lateinit var questionTextView : TextView
    private lateinit var androidVersionTextView: TextView
    private var correctQuestions=0.000
    private val TAG = "MainActivity"
    private var questionsAnswered= mutableListOf<Question>()
    private val KEY_INDEX = "index"
    private val REQUEST_CODE_CHEAT = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        title = "GeoQuiz"

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0

        /*
        //é ums intância da QuizviewModel, na qual retorna um provider, retorna
        val provider: ViewModelProvider = ViewModelProviders.of(this)
        //uma QuizViewModel associada a atividade atual
        val quizViewModel = provider.get(QuizViewModel::class.java)
        Log.d(TAG,"Got a QuizViewModel: $quizViewModel")
        */

        //Localiza as views, a classe R é como se fosse a chefe das classes do android
        trueButton = findViewById<Button>(R.id.bt_verdade)
        falseButton = findViewById<Button>(R.id.bt_falso)
        previewButton = findViewById<ImageButton>(R.id.preview_button)
        nextButton = findViewById<ImageButton>(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        cheatButton= findViewById(R.id.cheat_button)
        androidVersionTextView = findViewById(R.id.android_version_text_view)

        trueButton.setOnClickListener {
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

        /*um intent é um objeto que o componente pode usar para se comunicar com o OS
        //quando passamos uma activity::class.java, referenciamos a classe que deve ser iniciada
        e em qual pacote a classe pode ser encontrada
        O activityManager verifica o manifest em busca de uma Class com o mesmo nome, se não
        encontrar, ele lança uma exception de não encontrada, que crasha o app
        Essa intent criada é explícita, usada para iniciar activities (utilizada para iniciar
            activity da mesma aplicação)
        Quando se deseja iniciar uma activity de outra aplicação, se utiliza Intent implícita
        Quando inicia a activity do cheat, a Mainactivity informa a questão corrente
        * A atividade iniciada para um resultado, envia um código de requisição que será
        *   recebido pela classe filha
        *
        */
        cheatButton.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //verifica a versão do android, SDK int, que é a versão do android
                // com a versão M (inicial de Marshmallow)
                //listed at developer.android.com/reference/android/os/Build.VERSION_CODES.html

            }
            startActivity(Intent(this, CheatActivity::class.java))
            //startActivityForResult(intent,REQUEST_CODE_CHEAT)
        }
        updateQuestion()

        androidVersionTextView.setText("Versão:" +Build.VERSION.SDK_INT.toString())
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.getCurrentIndex())
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