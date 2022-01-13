package com.bignerdranch.android.criminalintent


//import android.support.v4.app.Fragment //Verificar se é a importacão correta
import android.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class CrimeFragment : Fragment() {

    // Lateinit: Iniciação posterior
    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

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

        //View.findView é a unica função suportada pelo fragmento, ao contrário de activity
        titleField = view.findViewById(R.id.crime_title) as EditText
        dateButton = view.findViewById(R.id.crime_date) as Button //importa como botão
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox //importa checkbox

        dateButton.apply {
            text = crime.date.toString()
            isEnabled = false // botão desativado não pode responder ao clique
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        /*
        * Funções que se envolvem durante o ciclo de vida do texto
        * Não somente com interação do usuário, mas sim durante qualquer alteração de dados ou view
        * */
        val titleWatcher = object : TextWatcher{
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //Blank Space
            }

            //no exemplo está sequence, mas nessa implementação está somente como "s"
            //"s" é o input do usuário
            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int)
            {
                crime.title=s.toString()
            }
            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }
        }

        //aplica alteração quando há mudança, recebendo isChecked na variável isSolved
        solvedCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                crime.isSolved = isChecked
            }
        }




        titleField.addTextChangedListener(titleWatcher)
    }
}