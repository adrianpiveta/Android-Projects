package com.bignerdranch.android.criminalintent

import android.icu.text.CaseMap
import java.util.*

/*
*  Os valores recebidos pela var com "=" é o valor padrão
* UUID é uma classe utilitária que já vem com o framework Android, ela gera ids
*   aleatórios e únicos de forma fácil, quando chama randomUUID(), ele gera um
*   id aleatório unico;
* Quando se inicia a data com Date(), ela retorna a data corrente*/
data class Crime ( val id: UUID = UUID.randomUUID(),
                   var title: String = "",
                   var date: Date = Date(),
                   var isSolved: Boolean = false)