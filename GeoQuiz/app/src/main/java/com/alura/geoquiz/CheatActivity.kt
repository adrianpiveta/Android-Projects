package com.alura.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

//armazena se a resposta é verdadeira, esse tipo de nome evita colisão com extra
//   de outros apps
private val EXTRA_ANSWER_IS_TRUE="com.alura.geoquiz.answer_is_true"
private var answerIsTrue = false
private lateinit var answerTextView: TextView
private lateinit var showAnswerButton: Button
const val EXTRA_ANSWER_SHOWN = "com.alura.geoquiz.answer_shown"

class CheatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        //se não encontra o valor, retorna o valor padrão como falso
        // get intent() sempre retorna o intent que iniciou a activity
        answerIsTrue=intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        /* Vai definir a ação quando clicar no botão de cheat
        *  a var answerText irá ter como respostasdo
        * answerIsTrue for verdade, irá ser definida pela String do True Button
        * já quando não corresponder a String de True Button, irá receber o valor
        *  de false button
        * */
        showAnswerButton.setOnClickListener {
            var answerText = when{
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShowResult(true)
            //answerTextView.setText((answerIsTrue).toString())
        }
    }

    /*
    * COmpanion Object é um método que funciona de forma semelhante a estático,
    * funciona quando a classe sem ser instanciada
    *
    * Essa função irá criar uma nova intent, como entradas, ela pega o context
    *   e a resposta, depois ela coloca esse intent em um novo extra, com chave
    *   e valor, na qual consegue chamar a classe desejada. Permite a outras
    *   classes chamar intents mais facilmente
    *Se precisar colocar mais argumentos, pode colocar vários extras em uma
    *   única intent.
    *
    * Usando Intent.getBooleanExtra(String, Boolean), é possivel retornar valor
    *   de uma extra
    */
    companion object{
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent{
            return Intent(packageContext, CheatActivity::class.java).apply{
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }

    /*
    * Quando pede para mostrar a resposta, ao voltar, a intent é informada sobre o resultado,
    *       que solicitou o cheat
    * */
    private fun setAnswerShowResult(isAnswerShow: Boolean){
        val data = intent.apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShow)
        }
        setResult(Activity.RESULT_OK, data)
    }
}