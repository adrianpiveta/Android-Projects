package livrokotlin.com.teste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tela2.*

class Tela2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)


        val nome = intent.getStringExtra("nome")
        //get int extra, pede um valor padrão (2o parametro)
        val idade = intent.getIntExtra("idade",-1)
        val cliente = intent.getParcelableExtra<Cliente>("cliente")
        /*
        * O Parcelable é a forma de passar extras recomendade pela google
        * de implementação mais completa e com melhor desempenho*/
        textMensagem.text= if(cliente!=null){
            "nome: ${cliente.nome} | idade: ${cliente.codigo}"
        } else{
            "nome: $nome | Idade: $idade"
        }

        buttonToast.setOnClickListener {
            val texto = editTexto.text.toString()
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }

        buttonTela2.setOnClickListener {
            val intent = Intent(this, Tela2Activity::class.java)
            startActivity(intent)
        }
    }
}