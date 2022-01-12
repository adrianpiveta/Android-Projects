package com.bignerdranch.android.criminalintent

import android.arch.lifecycle.ViewModel


// dois ponto ":" extende
// Se der ruim, verificar importação do ViewModel
class CrimeListViewModel: ViewModel() {

    val crimes = mutableListOf<Crime>()

    init{
        for(i in 0 until 100){
            val crime = Crime()
            // cifrão retorna valor da variável
            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0 //True or false, de acordo com ser par ou impar
            crimes += crime
        }
    }
}