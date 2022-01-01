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

private var currentIndex=0
class QuizViewModel : ViewModel() {
    init{
        Log.d(TAG,"qvm started")
    }
    //chamada quando a função é limpa
    override fun onCleared(){
        super.onCleared() //chamada método pai
        Log.d(TAG,"instance be destroyed") //LOG no Logcat
    }
}