package livrokotlin.com.calculadoradebitcoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL
import java.text.NumberFormat
import java.util.*
import android.R.id.*
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.bloco_cotacao.*
import kotlinx.android.synthetic.main.bloco_entrada.*
import kotlinx.android.synthetic.main.bloco_saida.*

class MainActivity : AppCompatActivity() {

    val API_URL = "https://www.mercadobitcoin.net/api/BTC/ticker/"
    var cotacaoBitcoin: Double = 00.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buscarCotacao()
        var txt_cotacao = findViewById<View>(R.id.txt_cotacao)

        btn_calcular.setOnClickListener {
            calcular()
        }
    }

    fun buscarCotacao() {

        doAsync {
            val resposta = URL(API_URL).readText()

            // de u json, obtém um objeto e do objeto o valor da chave
            cotacaoBitcoin = JSONObject(resposta).getJSONObject(
                "ticker").getDouble("last")

            // formata para a instancia local
            val f = NumberFormat.getCurrencyInstance(
                Locale("pt", "br")
            )

            val cotacaoFormatada = f.format(cotacaoBitcoin)

            uiThread {
                txt_cotacao.setText("$cotacaoFormatada")
            }
        }
    }

    fun calcular(){
        if (txt_valor.text.isEmpty()){
            // seta o campo de texto um alerta de erro, fica vermelho
            txt_valor.error="Digite um valor"
            return
        } else{
            // obtem o texto digitado, em string e passa para double
                //text é uma especie de objeto
                    // replace troca virgula por ponto
            val valorDigitado = txt_valor.text.toString().
            replace(",",".").toDouble()

            //aqui no resultado verificamos se é um valor maior que zero
            //caso a API não responda, não caíra em divisão por zero
            val resultado = if(cotacaoBitcoin>0) valorDigitado/cotacaoBitcoin
            else 0.0

            //formata para ter 8 casas decimais
            txt_qtd_bitcoins.text = "%.8f".format(resultado)
        }
    }
}