package livrokotlin.com.calculadoradebitcoin

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL
import java.text.NumberFormat
import java.util.*
import android.R.id.*
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bloco_cotacao.*
import kotlinx.android.synthetic.main.bloco_entrada.*
import kotlinx.android.synthetic.main.bloco_saida.*

class MainActivity : AppCompatActivity() {

    val ID_REQUISICAO_READ_CONTACTS = 1
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

        btn_permissao.setOnClickListener {
            requerirPermissao(Manifest.permission.CAMERA,
                ID_REQUISICAO_READ_CONTACTS)
        }
    }

    // verifica o resultado da requisição de permissões
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ID_REQUISICAO_READ_CONTACTS) {
            if (grantResults.size > 0 && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    this, "Permissão concedida",
                    Toast.LENGTH_LONG
                )
                //A	permissão	foi	concedida,	o	aplicativo	pode	utilizar o recurso
            } else {
                //A permissão não foi concedida, recurso off
                Toast.makeText(
                    this, "Permissão não concedida",
                    Toast.LENGTH_LONG
                )
            }
        }
    }

    fun buscarCotacao() {

        doAsync {
            val resposta = URL(API_URL).readText()

            // de u json, obtém um objeto e do objeto o valor da chave
            cotacaoBitcoin = JSONObject(resposta).getJSONObject(
                "ticker"
            ).getDouble("last")

            // formata para a instancia local
            val f = NumberFormat.getCurrencyInstance(
                Locale("pt", "br")
            )

            val cotacaoFormatada = f.format(cotacaoBitcoin)

            uiThread {
                txt_cotacao.setText("$cotacaoFormatada")
                criaNotificacao(
                    "foram aualizados os" +
                            " valores", "Valores novos"
                )
            }
        }
    }

    fun calcular() {
        if (txt_valor.text.isEmpty()) {
            // seta o campo de texto um alerta de erro, fica vermelho
            txt_valor.error = "Digite um valor"
            return
        } else {
            // obtem o texto digitado, em string e passa para double
            //text é uma especie de objeto
            // replace troca virgula por ponto
            val valorDigitado = txt_valor.text.toString().replace(",", ".").toDouble()

            //aqui no resultado verificamos se é um valor maior que zero
            //caso a API não responda, não caíra em divisão por zero
            val resultado = if (cotacaoBitcoin > 0) valorDigitado / cotacaoBitcoin
            else 0.0

            //formata para ter 8 casas decimais
            txt_qtd_bitcoins.text = "%.8f".format(resultado)
        }
    }

    // definindo Activity, pode ser reutilizado esta fun
    fun Activity.criaNotificacao(mensagem: String, titulo: String) {
        //chanelID é o canal de notificção (que há escolhas a partir do android 8)
        val nBuilder = NotificationCompat.Builder(
            this@MainActivity,
            "defalt"
        )

        // obtém o serviço de notificação
        val notificationManager = this@MainActivity.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        //verifica a versão da buil e compara se é android O ou posterior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // id default, Name como nome app, e importância é uma constante
            var chanel = NotificationChannel(
                "Default", "${R.string.app_name}",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(chanel)
        }

        // define o ícone da notificação
        nBuilder.setSmallIcon(R.mipmap.ic_launcher)

        nBuilder.setContentText(mensagem)
        nBuilder.setContentTitle(titulo)

        val notificacao = nBuilder.build()


        // cria um Id de notificação e passa a notificação so serviço
        notificationManager.notify(1, notificacao)
    }

    fun requerirPermissao(permissao: String, codigoRequisicao: Int) {
        //exemplo de permissão: Manifest.permission.READ_CONTACTS
        // mesmo sendo uma unica permissão, precisa ir dentro do array
        if (ContextCompat.checkSelfPermission(this,
             permissao)==
              PackageManager.PERMISSION_GRANTED) {
            //permissão OK
        } else{
        //sem permissão
        ActivityCompat.requestPermissions(
            this,
            arrayOf(permissao), codigoRequisicao)
        }
    }
}