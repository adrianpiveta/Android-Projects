package com.bignerdranch.android.criminalintent


//import android.support.v4.app.Fragment //Verificar se é a importacão correta
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

class CrimeFragment : Fragment() {

    // Lateinit: Iniciação posterior
    private lateinit var crime: Crime
    private lateinit var titleFiels: EditText

    //todo modificador é público, até que se declare o contrário
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    //A view é inflada com o fragment_crime, que faz ser carregado junto, no caso com R.layout.***
    // se passa o ID
    // essa função é chamada quando se cria a view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime, container, false)

        titleFiels = view.findViewById(R.id.crime_title) as EditText
        return view
    }
}