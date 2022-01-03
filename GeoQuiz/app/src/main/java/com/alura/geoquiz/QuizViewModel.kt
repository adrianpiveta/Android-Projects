package com.alura.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

private val questionBank = listOf(
    Question(R.string.question_amazonas, true),
    Question(R.string.question_australia, true),
    Question(R.string.question_bahia, false),
    Question(R.string.question_sampa, false),
    Question(R.string.question_rio, true))

var currentIndex=0


class QuizViewModel : ViewModel() {
    init{
        Log.d(TAG,"qvm started")
    }
    //chamada quando a função é limpa
    override fun onCleared(){
        super.onCleared() //chamada método pai
        Log.d(TAG,"instance be destroyed") //LOG no Logcat
    }
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun setCurrentIndex(index: Int): Boolean{
        if(questionBank.size>index || index>=0){
            currentIndex=index
            return true
        }
        else{
            currentIndex=0
            return false
        }
    }

    fun getQuestionBankSize(): Int {
        return questionBank.size
    }

    fun getCurrentIndex(): Int{
        return currentIndex
    }

    //formato de Question
    fun getCurrentQuestion(): Question{
        return questionBank[currentIndex]
    }

    //declaramos nome: tipo
    fun getQuestionByIndice(indice: Int): Question{
        if(indice< (questionBank.size)){
            return questionBank.get(indice)
        }
        return questionBank.get(0)
    }


    fun moveToNext(){
        currentIndex = (currentIndex++) % questionBank.size
    }
}